package br.com.fiap.postech.customers.interfaces.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
        String name,
        @NotBlank @Email String email,
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