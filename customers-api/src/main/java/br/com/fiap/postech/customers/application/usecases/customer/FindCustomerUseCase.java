package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;

import java.util.List;

/**
 * Use case for finding customers based on specific filters.
 */
public interface FindCustomerUseCase {

    /**
     * Filters customers based on the provided filter criteria.
     *
     * @param customerFilter the filter criteria for finding customers
     * @return a list of customers that match the filter criteria
     */
    List<Customer> filter(final CustomerFilter customerFilter);

    Customer findById(Long customerId);

}