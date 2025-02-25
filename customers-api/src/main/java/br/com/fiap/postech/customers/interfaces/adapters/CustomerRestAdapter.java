package br.com.fiap.postech.customers.interfaces.adapters;

import br.com.fiap.postech.customers.domain.model.Address;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerRequestDTO;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerResponseDTO;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerUpdateRequestDTO;
import org.springframework.stereotype.Component;

/**
 * Adapter class for converting between Customer domain model and various DTOs.
 */
@Component
public class CustomerRestAdapter {

    public Customer toDomain(CustomerRequestDTO dto) {
        final Address address = new Address(
                dto.zipCode(),
                dto.address(),
                dto.addressNumber(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                dto.complement()
        );
        return new Customer(
                dto.name(),
                dto.email(),
                dto.phone(),
                dto.cellPhone(),
                address
        );
    }

    public Customer toDomain(CustomerUpdateRequestDTO dto) {
        final Address address = new Address(
                dto.zipCode(),
                dto.address(),
                dto.addressNumber(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                dto.complement()
        );
        return new Customer(
                dto.name(),
                null,
                dto.phone(),
                dto.cellPhone(),
                address
        );
    }

    public CustomerResponseDTO toResponse(Customer saved) {
        final Address address = saved.getAddress();
        return new CustomerResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getCellPhone(),
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