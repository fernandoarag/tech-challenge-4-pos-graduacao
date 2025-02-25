package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.application.exception.custom.InvalidInputException;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import org.springframework.stereotype.Service;

/**
 * Service implementation of the DeleteCustomerUseCase interface.
 * Handles the deletion of a customer.
 */
@Service
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    /**
     * Constructs a new DeleteCustomerUseCaseImpl with the specified CustomerGateway.
     *
     * @param customerGateway the gateway to access customer data
     */
    public DeleteCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    /**
     * Deletes a customer by their ID.
     * Validates the ID and checks if the customer exists.
     *
     * @param customerID the ID of the customer to delete
     * @throws InvalidInputException     if the ID is null
     * @throws CustomerNotFoundException if the customer with the specified ID is not found
     */
    public void deleteCustomerById(final Long customerID) {
        if (customerID == null) {
            throw new InvalidInputException("ID cannot be null");
        }
        customerGateway.findById(customerID).orElseThrow(() -> new CustomerNotFoundException("id", customerID.toString()));
        customerGateway.deleteById(customerID);
    }

    /**
     * Deletes a customer by their email.
     * Checks if the customer exists by email.
     *
     * @param email the email of the customer to delete
     * @throws CustomerNotFoundException if the customer with the specified email is not found
     */
    @Override
    public void deleteCustomerByEmail(final String email) {
        Customer customer = customerGateway.findCustomerEntityByEmail(email).orElseThrow(() -> new CustomerNotFoundException("email", email));
        customerGateway.deleteById(customer.getId());
    }
}