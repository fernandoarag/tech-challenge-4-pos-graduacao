package br.com.fiap.postech.orders.interfaces.dto;

import br.com.fiap.postech.orders.domain.entities.Address;
import br.com.fiap.postech.orders.infrastructure.api.models.Customer;

public record CustomerApiResponseExternalDTO(Long id,
                                             String name,
                                             String email,
                                             String phone,
                                             String cellPhone,
                                             String zipCode,
                                             String address,
                                             String addressNumber,
                                             String neighborhood,
                                             String city,
                                             String state,
                                             String complement
) {
    public Customer toDomain() {
        return new Customer(
                this.id,
                this.name,
                this.email,
                new Address(
                        this.zipCode,
                        this.address,
                        this.addressNumber,
                        this.neighborhood,
                        this.city,
                        this.state,
                        this.complement
                )
        );
    }
}
