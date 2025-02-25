package br.com.fiap.postech.customers.application.usecases.customer;

import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import br.com.fiap.postech.customers.interfaces.gateway.database.CustomerGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FindCustomerUseCaseImplTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private FindCustomerUseCaseImpl findCustomerUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void filterReturnsCustomersWhenFilterMatches() {
        CustomerFilter filter = new CustomerFilter();
        Customer customer = new Customer();
        List<Customer> expectedCustomers = List.of(customer);
        when(customerGateway.filter(filter)).thenReturn(expectedCustomers);

        List<Customer> actualCustomers = findCustomerUseCaseImpl.filter(filter);

        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    void filterReturnsEmptyListWhenNoCustomersMatch() {
        CustomerFilter filter = new CustomerFilter();
        when(customerGateway.filter(filter)).thenReturn(Collections.emptyList());

        List<Customer> actualCustomers = findCustomerUseCaseImpl.filter(filter);

        assertEquals(Collections.emptyList(), actualCustomers);
    }

    @Test
    void filterReturnsEmptyListWhenFilterIsNull() {
        when(customerGateway.filter(null)).thenReturn(Collections.emptyList());

        List<Customer> actualCustomers = findCustomerUseCaseImpl.filter(null);

        assertEquals(Collections.emptyList(), actualCustomers);
    }
}