package br.com.fiap.postech.customers.application.usecases.customer;

/**
 * Use case for deleting a customer.
 */
public interface DeleteCustomerUseCase {

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     */
    void deleteCustomerById(final Long id);

    /**
     * Deletes a customer by their email.
     *
     * @param email the email of the customer to delete
     */
    void deleteCustomerByEmail(final String email);

}