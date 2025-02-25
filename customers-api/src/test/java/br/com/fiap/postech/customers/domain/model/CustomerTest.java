package br.com.fiap.postech.customers.domain.model;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.application.usecases.customer.UpdateCustomerUseCaseImpl;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private UpdateCustomerUseCaseImpl updateCustomerUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateCustomerWithoutEmailCustomerByIdUpdatesAllFields() {
        Long customerId = 1L;
        final Address existingCustomerAddress = new Address("00000", "Old St", "1", "Old Neighborhood", "Old City", "Old State", "Old Complement");
        Customer existingCustomer = new Customer(customerId, "Old Name", "old.email@example.com", "111111111", "222222222", existingCustomerAddress);
        final Address customerUpdateAddress = new Address("12345", "New St", "100", "New Neighborhood", "New City", "New State", "New Complement");
        Customer customerUpdate = new Customer("New Name", "old.email@example.com", "333333333", "444444444", customerUpdateAddress);
        when(customerGateway.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerGateway.save(existingCustomer)).thenReturn(existingCustomer);

        Customer updatedCustomer = updateCustomerUseCaseImpl.updateCustomerById(customerId, customerUpdate);

        verify(customerGateway).save(existingCustomer);
        assertEquals("New Name", updatedCustomer.getName());
        assertEquals("old.email@example.com", updatedCustomer.getEmail());
        assertEquals("333333333", updatedCustomer.getPhone());
        assertEquals("444444444", updatedCustomer.getCellPhone());
        assertEquals("12345", updatedCustomer.getAddress().getZipCode());
        assertEquals("New St", updatedCustomer.getAddress().getName());
        assertEquals("100", updatedCustomer.getAddress().getAddressNumber());
        assertEquals("New Neighborhood", updatedCustomer.getAddress().getNeighborhood());
        assertEquals("New City", updatedCustomer.getAddress().getCity());
        assertEquals("New State", updatedCustomer.getAddress().getState());
        assertEquals("New Complement", updatedCustomer.getAddress().getComplement());
    }

    @Test
    void updateCustomerByIdDoesNotUpdateCustomerWithoutEmailId() {
        Long customerId = 1L;
        final Address existingCustomerAddress = new Address("00000", "Old St", "1", "Old Neighborhood", "Old City", "Old State", "Old Complement");
        Customer existingCustomer = new Customer(customerId, "Old Name", "old.email@example.com", "111111111", "222222222", existingCustomerAddress);
        final Address customerUpdateAddress = new Address("12345", "New St", "100", "New Neighborhood", "New City", "New State", "New Complement");
        Customer customerUpdate = new Customer(2L, "New Name", "new.email@example.com", "333333333", "444444444", customerUpdateAddress);
        when(customerGateway.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerGateway.save(existingCustomer)).thenReturn(existingCustomer);

        Customer updatedCustomer = updateCustomerUseCaseImpl.updateCustomerById(customerId, customerUpdate);

        verify(customerGateway).save(existingCustomer);
        assertEquals(customerId, updatedCustomer.getId());
    }


    @Test
    void updatingClientByIdThrowsInvalidInputExceptionWhenCustomerIsNotFound() {
        Long customerId = 1L;

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            updateCustomerUseCaseImpl.updateCustomerById(customerId, null);
        });

        assertEquals("Customer with id: '1' not found!", exception.getMessage());
    }

    @Test
    void updateCustomerWithoutEmailCustomerByIdThrowsInvalidInputExceptionWhenCustomerIdIsNegative() {
        Long customerId = -1L;
        final Address address = new Address("12345", "New St", "100", "New Neighborhood", "New City", "New State", "New Complement");
        Customer customerUpdate = new Customer("New Name", "new.email@example.com", "333333333", "444444444", address);

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            updateCustomerUseCaseImpl.updateCustomerById(customerId, customerUpdate);
        });

        assertEquals("Customer with id: '-1' not found!", exception.getMessage());
    }
}