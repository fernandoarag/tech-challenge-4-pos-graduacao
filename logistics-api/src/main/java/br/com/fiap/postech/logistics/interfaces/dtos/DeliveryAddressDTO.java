package br.com.fiap.postech.logistics.interfaces.dtos;

import java.util.UUID;

public record DeliveryAddressDTO(
        UUID id,
        String zipCode,
        String name,
        String addressNumber,
        String neighborhood,
        String city,
        String state,
        String complement
) {}
