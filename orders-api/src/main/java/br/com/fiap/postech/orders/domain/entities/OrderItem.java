package br.com.fiap.postech.orders.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private UUID id;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(UUID id, Long productId, int quantity, BigDecimal unitPrice, Order order) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;

        calculateTotalPrice();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPrice(); // Recalcula o totalPrice quando o preço unitário é alterado
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        calculateTotalPrice(); // Recalcula o totalPrice quando o preço unitário é alterado
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        if (this.unitPrice == null) {
            throw new IllegalStateException("unitPrice não pode ser nulo ao calcular totalPrice");
        }
        this.totalPrice = BigDecimal.valueOf(this.quantity).multiply(this.unitPrice);
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", order=" + order +
                '}';
    }
}
