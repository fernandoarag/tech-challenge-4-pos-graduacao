package br.com.fiap.postech.orders.infrastructure.mapper;

import br.com.fiap.postech.orders.domain.entities.Order;
import br.com.fiap.postech.orders.domain.entities.OrderItem;
import br.com.fiap.postech.orders.infrastructure.persistence.OrderEntity;
import br.com.fiap.postech.orders.infrastructure.persistence.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderEntity toEntity(final Order order) {
        OrderEntity entity = new OrderEntity(order.getId(), order.getUpdatedAt(), order.getCreatedAt(),
                order.getEstimatedDeliveryDate(), order.getPaymentMethod(), order.getDeliveryAddress(), Collections.emptyList(),
                order.getCustomerId(), order.getStatus());

        // Usando stream().map() para transformar a lista de OrderItem em OrderItemEntity
        List<OrderItemEntity> orderItems = order.getItems().stream()
                .map(orderItem -> orderItemMapper.toEntity(orderItem, entity)) // Mapeia cada OrderItem para OrderItemEntity
                .toList(); // Coleta os resultados em uma lista

        entity.setItems(orderItems); // Define a lista de OrderItemEntity no OrderEntity

        return entity;
    }

    public Order toModel(OrderEntity entity) {
        final Order order = new Order(entity.getId(), entity.getStatus(), entity.getCustomerId(), Collections.emptyList(),
                entity.getDeliveryAddress(), entity.getTotalAmount().doubleValue(), entity.getPaymentMethod(),
                entity.getEstimatedDeliveryDate());

        List<OrderItem> orderItems = entity.getItems().stream()
                .map(orderItem -> orderItemMapper.toModel(orderItem, order)) // Mapeia cada OrderItemEntity para OrderItem
                .toList();

        order.setItems(orderItems);

        return order;
    }
}
