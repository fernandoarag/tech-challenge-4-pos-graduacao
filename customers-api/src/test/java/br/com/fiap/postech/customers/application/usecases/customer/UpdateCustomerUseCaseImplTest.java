package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.application.exception.custom.InvalidInputException;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateCustomerUseCaseImplTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private UpdateCustomerUseCaseImpl updateCustomerUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateCustomerWithoutEmailCustomerByIdSuccessfully() {
        Long customerId = 1L;
        Customer customer = new Customer();
        Customer customerUpdate = new Customer();
        when(customerGateway.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerGateway.save(customer)).thenReturn(customer);

        Customer updatedCustomer = updateCustomerUseCaseImpl.updateCustomerById(customerId, customerUpdate);

        verify(customerGateway).save(customer);
        assertEquals(customer, updatedCustomer);
    }

    @Test
    void updateCustomerWithoutEmailCustomerByIdThrowsInvalidInputExceptionWhenIdIsNull() {
        Customer customerUpdate = new Customer();

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            updateCustomerUseCaseImpl.updateCustomerById(null, customerUpdate);
        });

        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void updateCustomerWithoutEmailCustomerByIdThrowsCustomerNotFoundExceptionWhenCustomerDoesNotExist() {
        Long customerId = 1L;
        Customer customerUpdate = new Customer();
        when(customerGateway.findById(customerId)).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            updateCustomerUseCaseImpl.updateCustomerById(customerId, customerUpdate);
        });

        assertEquals("Customer with id: '1' not found!", exception.getMessage());
    }
}