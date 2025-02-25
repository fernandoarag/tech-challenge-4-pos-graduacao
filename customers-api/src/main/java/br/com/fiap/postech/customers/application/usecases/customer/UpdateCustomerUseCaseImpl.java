package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.application.exception.custom.InvalidInputException;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import org.springframework.stereotype.Service;

/**
 * Service implementation of the UpdateCustomerUseCase interface.
 * Handles updating a customer.
 */
@Service
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    /**
     * Constructs a new UpdateCustomerUseCaseImpl with the specified CustomerGateway.
     *
     * @param customerGateway the gateway to access customer data
     */
    public UpdateCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    /**
     * Updates a customer by their ID.
     * Validates the ID and checks if the customer exists.
     *
     * @param customerID     the ID of the customer to update
     * @param customerUpdate the customer data to update
     * @return the updated customer
     * @throws InvalidInputException     if the ID is null
     * @throws CustomerNotFoundException if the customer with the specified ID is not found
     */
    @Override
    public Customer updateCustomerById(final Long customerID, final Customer customerUpdate) {
        if (customerID == null) {
            throw new InvalidInputException("ID cannot be null");
        }

        final Customer customer = customerGateway
                .findById(customerID)
                .orElseThrow(() -> new CustomerNotFoundException("id", customerID.toString()));

        customer.updateCustomerWithoutEmail(customerID, customerUpdate);

        return customerGateway.save(customer);
    }
}