package br.com.fiap.postech.orders.application.usecases;

import br.com.fiap.postech.orders.domain.entities.Order;
import br.com.fiap.postech.orders.domain.enums.OrderStatus;
import br.com.fiap.postech.orders.infrastructure.api.CustomerGateway;
import br.com.fiap.postech.orders.infrastructure.api.ProductGateway;
import br.com.fiap.postech.orders.infrastructure.api.models.Customer;
import br.com.fiap.postech.orders.infrastructure.api.models.Product;
import br.com.fiap.postech.orders.infrastructure.exception.*;
import br.com.fiap.postech.orders.infrastructure.gateway.impl.OrderRepositoryGatewayImpl;
import br.com.fiap.postech.orders.infrastructure.messaging.producer.KafkaProducerService;
import br.com.fiap.postech.orders.infrastructure.messaging.OrderCreatedEvent;
import br.com.fiap.postech.orders.interfaces.dto.QuantityItemResquestDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CreateOrderUsecase {

    private final OrderRepositoryGatewayImpl orderRepositoryGateway;
    private final CustomerGateway customerGateway;
    private final ProductGateway productGateway;
    private final KafkaProducerService kafkaProducerService;

    public CreateOrderUsecase(OrderRepositoryGatewayImpl orderRepositoryGateway, CustomerGateway customerGateway,
                              ProductGateway productGateway, KafkaProducerService kafkaProducerService) {
        this.orderRepositoryGateway = orderRepositoryGateway;
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Transactional
    public Order execute(Order order) {
        validateOrder(order);

        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        try {
            List<QuantityItemResquestDTO> quantityItemRequestDTO = order.getItems()
                    .stream()
                    .map(item -> QuantityItemResquestDTO.fromValues(item.getProductId(), item.getQuantity()))
                    .toList();

            productGateway.subtractStocks(quantityItemRequestDTO);

        } catch (Exception e) {
            log.error("Erro ao salvar o pedido", e);
            throw new InsufficientStockException("Erro ao salvar o pedido");
        }

        Order savedOrder = orderRepositoryGateway.save(order);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getDeliveryAddress()
        );
        // Publica o evento
        kafkaProducerService.sendOrderCreatedEvent(orderCreatedEvent);

        return savedOrder;
    }

    private void validateOrder(Order order) {
        if (order.getCustomerId() == null) {
            throw new NoCustomerException("O pedido deve ter um cliente associado.");
        }
        Customer customer = customerGateway.getCustomerById(order.getCustomerId());
        if (customer == null) {
            throw new CustomerNotFoundException("Cliente não encontrado para o ID: " + order.getCustomerId());
        }

        if (order.getDeliveryAddress() == null) {
            throw new InvalidAddressException("O endereço de entrega não pode ser nulo ou vazio.");
        }

        if (order.getItems().isEmpty()) {
            throw new NoItemException("Pedido vazio.");
        }

        if (order.getDeliveryAddress().getZipCode().isEmpty() ||
                order.getDeliveryAddress().getName().isEmpty() ||
                order.getDeliveryAddress().getAddressNumber().isEmpty() ||
                order.getDeliveryAddress().getNeighborhood().isEmpty() ||
                order.getDeliveryAddress().getCity().isEmpty() ||
                order.getDeliveryAddress().getState().isEmpty() ||
                order.getDeliveryAddress().getComplement().isEmpty()) {
            throw new InvalidAddressException("O endereço precisa estar completamente preenchido");
        }
        order.getItems().forEach(item -> {
            if (item.getQuantity() <= 0) {
                throw new InvalidQuantityException("A quantidade deve ser maior que zero para o produto: " + item.getProductId());
            }
            if (item.getTotalPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidPriceException("O valor total do item não pode ser negativo para o produto: " + item.getProductId());
            }

            Product product = productGateway.getProductById(item.getProductId());
            if (product == null) {
                throw new ProductNotFoundException("Produto não encontrado: " + item.getProductId());
            }
            if (!productGateway.isInStock(item.getProductId(), item.getQuantity())) {
                throw new InsufficientStockException("Estoque insuficiente para o produto: " + item.getProductId());
            }
        });
    }
}
