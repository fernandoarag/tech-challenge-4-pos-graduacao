package br.com.fiap.postech.orders.interfaces.dto;

import br.com.fiap.postech.orders.domain.entities.OrderItem;

import java.math.BigDecimal;

record OrderItemResponseDTO(
        Long productId,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {
    public static OrderItemResponseDTO fromDomain(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getProductId(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getTotalPrice()
        );
    }
}