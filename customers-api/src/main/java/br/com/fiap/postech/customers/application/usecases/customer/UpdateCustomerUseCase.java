package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.domain.model.Customer;

/**
 * Use case for updating a customer.
 */
public interface UpdateCustomerUseCase {

    /**
     * Updates a customer by their ID.
     *
     * @param id       the ID of the customer to update
     * @param customer the customer data to update
     * @return the updated customer
     */
    Customer updateCustomerById(final Long id, final Customer customer);

}