package br.com.fiap.postech.customers.interfaces.gateway.database;

import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;

import java.util.List;
import java.util.Optional;

/**
 * Gateway interface for customer-related database operations.
 */
public interface CustomerGateway {

    /**
     * Saves or updates a customer.
     *
     * @param customer the customer to save or update
     * @return the saved or updated customer
     */
    Customer save(final Customer customer);

    /**
     * Filters customers based on the given CustomerFilter criteria.
     *
     * @param customerFilter the filter criteria
     * @return a list of customers that match the filter criteria
     */
    List<Customer> filter(final CustomerFilter customerFilter);

    /**
     * Finds a customer by ID.
     *
     * @param id the ID of the customer to find
     * @return an Optional containing the found customer, or empty if not found
     */
    Optional<Customer> findById(final Long id);

    /**
     * Finds a customer by email.
     *
     * @param email the email address to search for
     * @return an Optional containing the found customer, or empty if not found
     */
    Optional<Customer> findCustomerEntityByEmail(final String email);

    /**
     * Deletes a customer by ID.
     *
     * @param id the ID of the customer to delete
     */
    void deleteById(final Long id);
}