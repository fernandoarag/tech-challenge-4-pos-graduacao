package br.com.fiap.postech.customers.interfaces.dtos;

public record CustomerUpdateRequestDTO(
        String name,
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
}
