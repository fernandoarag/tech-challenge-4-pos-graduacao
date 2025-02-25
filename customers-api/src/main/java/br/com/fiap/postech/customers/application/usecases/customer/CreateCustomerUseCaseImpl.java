package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.application.exception.custom.CustomerAlreadyExistsException;
import br.com.fiap.postech.customers.application.exception.custom.CustomerEmailCannotBeBlankException;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Service implementation of the CreateCustomerUseCase interface.
 * Handles the creation of a new customer.
 */
@Service
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    /**
     * Constructs a new CreateCustomerUseCaseImpl with the specified CustomerGateway.
     *
     * @param customerGateway the gateway to access customer data
     */
    public CreateCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    /**
     * Executes the use case to create a customer.
     * Validates the customer email and checks for existing customers with the same email.
     *
     * @param customer the customer to create
     * @return the created customer
     * @throws CustomerEmailCannotBeBlankException if the customer's email is blank
     * @throws CustomerAlreadyExistsException      if a customer with the same email already exists
     */
    public Customer execute(Customer customer) {
        if (StringUtils.isBlank(customer.getEmail())) {
            throw new CustomerEmailCannotBeBlankException();
        }
        if (customerGateway.findCustomerEntityByEmail(customer.getEmail()).isPresent()) {
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        return customerGateway.save(customer);
    }
}