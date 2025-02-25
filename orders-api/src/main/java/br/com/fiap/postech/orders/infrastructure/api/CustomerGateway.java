package br.com.fiap.postech.orders.infrastructure.api;

import br.com.fiap.postech.orders.infrastructure.api.models.Customer;

public interface CustomerGateway {
    Customer getCustomerById(Long customerId);
}
