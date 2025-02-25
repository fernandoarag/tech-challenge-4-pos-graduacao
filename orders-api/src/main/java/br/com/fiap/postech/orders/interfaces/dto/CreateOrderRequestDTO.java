package br.com.fiap.postech.orders.interfaces.dto;

import br.com.fiap.postech.orders.domain.entities.Address;
import br.com.fiap.postech.orders.domain.entities.Order;
import br.com.fiap.postech.orders.domain.entities.OrderItem;
import br.com.fiap.postech.orders.domain.enums.OrderStatus;
import br.com.fiap.postech.orders.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public record CreateOrderRequestDTO(

        @NotNull(message = "O ID do cliente é obrigatório")
        @Validated Long customerId,

        @NotEmpty(message = "A lista de itens não pode estar vazia")
        @Validated List<OrderItemRequestDTO> items,

        @NotNull(message = "O endereço não pode estar vazio")
        @Validated Address deliveryAddress,

        @NotNull(message = "O método de pagamento é obrigatório")
        @Validated PaymentMethod paymentMethod
) {
    // Método para converter DTO -> Domínio
    public Order toDomain() {
        Order order = new Order(
                OrderStatus.OPEN,
                customerId,
                deliveryAddress,
                paymentMethod,
                null, // estimatedDeliveryDate
                null  // createdAt
        );

        // Associa os itens e insere ao pedido
        for (OrderItemRequestDTO item : items) {
            OrderItem newOrder = item.toDomain(order);
            log.warn("Item: {}", newOrder);
            order.addItem(newOrder);
        }

        return order;
    }

    @Override
    public String toString() {
        return "CreateOrderRequestDTO{" +
                "customerId=" + customerId +
                ", items=" + items +
                ", deliveryAddress=" + deliveryAddress +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}