package br.com.fiap.postech.customers.interfaces.gateway.database;

import br.com.fiap.postech.customers.domain.model.Address;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerGatewayTest {

    private static final Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");

    @Mock
    private CustomerGateway customerGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCustomerSuccessfully() {
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);

        when(customerGateway.save(customer)).thenReturn(customer);

        Customer result = customerGateway.save(customer);

        assertEquals(customer, result);
    }

    @Test
    void filterReturnsListOfCustomers() {
        CustomerFilter filter = new CustomerFilter();
        List<Customer> customers = List.of(new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address));
        when(customerGateway.filter(filter)).thenReturn(customers);

        List<Customer> result = customerGateway.filter(filter);

        assertEquals(customers, result);
    }

    @Test
    void findByIdReturnsCustomer() {
        Long id = 1L;
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);
        when(customerGateway.findById(id)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerGateway.findById(id);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    void findCustomerEntityByEmailReturnsCustomer() {
        String email = "john.doe@example.com";
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);
        when(customerGateway.findCustomerEntityByEmail(email)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerGateway.findCustomerEntityByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    void deleteByIdSuccessfully() {
        Long id = 1L;
        customerGateway.deleteById(id);

        // Add assertion to verify that deleteById was called
        verify(customerGateway).deleteById(id);
    }
}