package br.com.fiap.postech.orders.infrastructure.messaging;

import br.com.fiap.postech.orders.domain.entities.Address;

import java.util.UUID;

public class OrderCreatedEvent {
    private UUID orderId;
    private Long customerId;
    private Address deliveryAddress;
    //updateTime

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(UUID orderId, Long customerId, Address address) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddress = address;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Address getAddress() {
        return deliveryAddress;
    }

    public void setAddress(Address address) {
        this.deliveryAddress = address;
    }
}
