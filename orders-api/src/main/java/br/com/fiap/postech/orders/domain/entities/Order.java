package br.com.fiap.postech.orders.domain.entities;

import br.com.fiap.postech.orders.domain.enums.OrderStatus;
import br.com.fiap.postech.orders.domain.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Order {

    @Getter
    @Setter
    private UUID id;

    @Setter
    @Getter
    private OrderStatus status;

    @Setter
    @Getter
    private Long customerId;

    @Setter
    private List<OrderItem> items = new ArrayList<>();

    @Setter
    @Getter
    private Address deliveryAddress;

    @Getter
    private double totalAmount;

    @Setter
    @Getter
    private PaymentMethod paymentMethod;

    @Setter
    @Getter
    private LocalDateTime estimatedDeliveryDate;
    @Getter
    @Setter
    private LocalDateTime createdAt;
    @Getter
    @Setter
    private LocalDateTime updatedAt;

    public Order(UUID id, OrderStatus status, Long customerId, List<OrderItem> items, Address deliveryAddress, double totalAmount, PaymentMethod paymentMethod, LocalDateTime estimatedDeliveryDate) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Order(OrderStatus orderStatus, UUID orderId, List<OrderItem> items, Address deliveryAddress, PaymentMethod paymentMethod, LocalDateTime estimatedDeliveryDate, LocalDateTime createdAt) {
        this.id = orderId;
        this.status = orderStatus;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.createdAt = createdAt;
    }

    public Order(
            OrderStatus status,
            Long customerId,
            List<OrderItem> items,
            Address deliveryAddress,
            PaymentMethod paymentMethod,
            LocalDateTime estimatedDeliveryDate,
            LocalDateTime createdAt
    ) {
        this.status = status;
        this.customerId = customerId;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.createdAt = createdAt;
    }

    public Order(OrderStatus status, Long customerId, Address deliveryAddress, PaymentMethod paymentMethod,
                 LocalDateTime estimatedDeliveryDate, LocalDateTime createdAt) {
        this.status = status;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.createdAt = createdAt;
    }

    public Order() {
    }

    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        calculateTotalAmount(); // Recalcula o totalAmount
    }

    public void removeItem(OrderItem item) {
        if (!this.items.remove(item)) {
            throw new IllegalArgumentException("Item não encontrado no pedido.");
        }
        calculateTotalAmount();
    }

    public void clearItems() {
        if (status != OrderStatus.OPEN) {
            throw new IllegalStateException("Não é possível remover itens de um pedido processado.");
        }
        this.items.clear();
        this.totalAmount = 0.0;
    }

    private void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(OrderItem::getTotalPrice) // Obtém o BigDecimal
                .filter(Objects::nonNull) // Evita NullPointerException
                .mapToDouble(BigDecimal::doubleValue) // Converte para double
                .sum();
    }

}