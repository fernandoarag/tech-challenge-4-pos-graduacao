package br.com.fiap.postech.logistics.application.factory;

import br.com.fiap.postech.logistics.domain.events.AddressEvent;
import br.com.fiap.postech.logistics.domain.factory.DeliveryAddressFactory;
import br.com.fiap.postech.logistics.domain.model.DeliveryAddress;
import br.com.fiap.postech.logistics.interfaces.dtos.DeliveryAddressDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeliveryAddressFactoryImpl implements DeliveryAddressFactory {

    public DeliveryAddress create(UUID id, String zipCode, String name, String addressNumber, String neighborhood,
                                  String city, String state, String complement) {
        return new DeliveryAddress(id, zipCode, name, addressNumber, neighborhood, city, state, complement);
    }

    public DeliveryAddress createFromEvent(AddressEvent event) {
        return new DeliveryAddress(
                UUID.randomUUID(),
                event.zipCode(),
                event.name(),
                event.addressNumber(),
                event.neighborhood(),
                event.city(),
                event.state(),
                event.complement()
        );
    }

    public DeliveryAddress createFromDTO(DeliveryAddressDTO dto) {
        return new DeliveryAddress(
                UUID.randomUUID(),
                dto.zipCode(),
                dto.name(),
                dto.addressNumber(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                dto.complement()
        );
    }
}
