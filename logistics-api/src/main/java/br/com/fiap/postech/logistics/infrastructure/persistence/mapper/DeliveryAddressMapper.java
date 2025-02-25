package br.com.fiap.postech.logistics.infrastructure.persistence.mapper;

import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.infrastructure.persistence.entity.DeliveryAddressEntity;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryAddressDTO;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressMapper {

    private final DeliveryAddressFactory deliveryAddressFactory;

    public DeliveryAddressMapper(DeliveryAddressFactory deliveryAddressFactory) {
        this.deliveryAddressFactory = deliveryAddressFactory;
    }

    public DeliveryAddress toDomain(DeliveryAddressEntity entity) {
        if (entity == null) return null;
        return deliveryAddressFactory.create(
                entity.getId(),
                entity.getZipCode(),
                entity.getName(),
                entity.getAddressNumber(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState(),
                entity.getComplement()
        );
    }

    public DeliveryAddressEntity toEntity(DeliveryAddress address) {
        if (address == null) return null;
        return DeliveryAddressEntity.create(
                null,
                address.getZipCode(),
                address.getName(),
                address.getAddressNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getComplement()
        );
    }

    public DeliveryAddressDTO toDTO(DeliveryAddress address) {
        return new DeliveryAddressDTO(
                address.getId(),
                address.getZipCode(),
                address.getName(),
                address.getAddressNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getComplement()
        );
    }
}
