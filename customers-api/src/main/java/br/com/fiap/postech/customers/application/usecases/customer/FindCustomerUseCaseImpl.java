package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation of the FindCustomerUseCase interface.
 * Handles finding customers based on specific filters.
 */
@Service
public class FindCustomerUseCaseImpl implements FindCustomerUseCase {

    private final CustomerGateway customerGateway;

    /**
     * Constructs a new FindCustomerUseCaseImpl with the specified CustomerGateway.
     *
     * @param customerGateway the gateway to access customer data
     */
    public FindCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    /**
     * Filters customers based on the provided filter criteria.
     *
     * @param customerFilter the filter criteria for finding customers
     * @return a list of customers that match the filter criteria
     */
    @Override
    public List<Customer> filter(CustomerFilter customerFilter) {
        return customerGateway.filter(customerFilter);
    }

    /**
     * Filters customers based on the provided id.
     *
     * @return a list of customers that match the filter criteria
     */
    @Override
    public Customer findById(final Long customerId) {
        return customerGateway.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("id", customerId.toString()));
    }

}