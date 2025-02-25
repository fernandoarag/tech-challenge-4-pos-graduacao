package br.com.fiap.postech.orders.infrastructure.mapper;

import br.com.fiap.postech.orders.domain.entities.Order;
import br.com.fiap.postech.orders.domain.entities.OrderItem;
import br.com.fiap.postech.orders.infrastructure.persistence.OrderEntity;
import br.com.fiap.postech.orders.infrastructure.persistence.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemEntity toEntity(OrderItem orderItem, OrderEntity orderEntity) {
        if (orderItem == null) {
            return null;
        }
        return new OrderItemEntity(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderEntity
        );
    }

    public OrderItem toModel(OrderItemEntity entity, Order order) {
        if (entity == null) {
            return null;
        }
        return new OrderItem(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                order
        );
    }
}
