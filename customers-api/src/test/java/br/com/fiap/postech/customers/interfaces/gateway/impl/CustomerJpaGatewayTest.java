package br.com.fiap.postech.customers.interfaces.gateway.impl;

import br.com.fiap.postech.customers.application.exception.custom.CustomerNotFoundException;
import br.com.fiap.postech.customers.domain.model.Address;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.entity.CustomerEntity;
import br.com.fiap.postech.customers.infrastructure.mapper.CustomerMapper;
import br.com.fiap.postech.customers.infrastructure.repository.CustomerRepository;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerJpaGatewayTest {

    private static final Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    private CustomerJpaGateway customerJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerJpaGateway = new CustomerJpaGateway(repository, mapper);
    }

    @Test
    void saveCustomerSuccessfully() {
        CustomerEntity entity = new CustomerEntity();
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);
        when(mapper.toEntity(customer)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(customer);

        Customer result = customerJpaGateway.save(customer);

        assertEquals(customer, result);
    }

    @Test
    void filterReturnsListOfCustomers() {
        CustomerFilter filter = new CustomerFilter();
        List<CustomerEntity> entities = List.of(new CustomerEntity());
        List<Customer> customers = List.of(new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address));
        when(repository.filter(filter)).thenReturn(entities);
        when(mapper.toDomain(entities.get(0))).thenReturn(customers.get(0));

        List<Customer> result = customerJpaGateway.filter(filter);

        assertEquals(customers, result);
    }

    @Test
    void findByIdReturnsCustomer() {
        Long id = 1L;
        CustomerEntity entity = new CustomerEntity();
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(customer);

        Optional<Customer> result = customerJpaGateway.findById(id);

        assertEquals(Optional.of(customer), result);
    }

    @Test
    void findCustomerEntityByEmailReturnsCustomer() {
        String email = "john.doe@example.com";
        CustomerEntity entity = new CustomerEntity();
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);
        when(repository.findCustomerEntityByEmail(email)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(customer);

        Optional<Customer> result = customerJpaGateway.findCustomerEntityByEmail(email);

        assertEquals(Optional.of(customer), result);
    }

    @Test
    void deleteByIdSuccessfully() {
        Long id = 1L;
        CustomerEntity entity = new CustomerEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(repository).deleteById(id);

        customerJpaGateway.deleteById(id);

        // Add assertion to verify that deleteById was called
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdThrowsCustomerNotFoundException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerJpaGateway.deleteById(id));
    }

}
