package br.com.fiap.postech.logistics.domain.events;

public record AddressEvent(
        String zipCode,
        String name,
        String addressNumber,
        String neighborhood,
        String city,
        String state,
        String complement
) {
}
