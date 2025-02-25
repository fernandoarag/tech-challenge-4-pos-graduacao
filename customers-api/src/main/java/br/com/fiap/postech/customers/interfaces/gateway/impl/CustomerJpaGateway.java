package br.com.fiap.postech.customers.interfaces.gateway.impl;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.mapper.CustomerMapper;
import br.com.fiap.postech.customers.infrastructure.repository.CustomerRepository;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CustomerGateway interface using JPA.
 */
@Component
@RequiredArgsConstructor
public class CustomerJpaGateway implements CustomerGateway {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    /**
     * Saves a customer to the repository.
     *
     * @param customer the customer to save
     * @return the saved customer
     */
    @Override
    public Customer save(final Customer customer) {
        var entity = mapper.toEntity(customer);
        return mapper.toDomain(repository.save(entity));
    }

    /**
     * Filters customers based on the provided filter criteria.
     *
     * @param customerFilter the filter criteria
     * @return a list of customers matching the filter criteria
     */
    public List<Customer> filter(CustomerFilter customerFilter) {
        return repository.filter(customerFilter).stream().map(mapper::toDomain).toList();
    }

    /**
     * Finds a customer by their ID.
     *
     * @param id the ID of the customer
     * @return an Optional containing the found customer, or empty if not found
     */
    public Optional<Customer> findById(final Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    /**
     * Finds a customer by their email.
     *
     * @param email the email of the customer
     * @return an Optional containing the found customer, or empty if not found
     */
    public Optional<Customer> findCustomerEntityByEmail(final String email) {
        return repository.findCustomerEntityByEmail(email).map(mapper::toDomain);
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param customerID the ID of the customer to delete
     * @throws CustomerNotFoundException if the customer with the given ID is not found
     */
    public void deleteById(final Long customerID) {
        repository.findById(customerID).orElseThrow(() -> new CustomerNotFoundException("id", customerID.toString()));
        repository.deleteById(customerID);
    }

}