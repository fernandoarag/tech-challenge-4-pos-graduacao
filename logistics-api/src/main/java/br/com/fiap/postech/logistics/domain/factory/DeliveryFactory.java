package br.com.fiap.postech.logistics.domain.factory;

import br.com.fiap.postech.logistics.domain.model.Courier;
import br.com.fiap.postech.logistics.domain.model.Delivery;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DeliveryFactory {
    Delivery create(UUID orderId, Long customerId, DeliveryAddress address);

    Delivery create(UUID orderId, Long customerId, DeliveryAddress address, Courier courier);

    Delivery create(UUID id, UUID orderId, Long customerId, Courier courierId,
                    DeliveryStatus status, DeliveryAddress address,
                    LocalDateTime createdAt, LocalDateTime deliveredAt);

}
