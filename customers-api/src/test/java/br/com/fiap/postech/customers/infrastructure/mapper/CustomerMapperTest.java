
package br.com.fiap.postech.customers.infrastructure.mapper;

import br.com.fiap.postech.customers.domain.model.Address;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
    }

    @Test
    void toDomainReturnsCustomerWithCorrectValues() {
        CustomerEntity entity = new CustomerEntity(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", "12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        Customer result = customerMapper.toDomain(entity);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("123456789", result.getPhone());
        assertEquals("987654321", result.getCellPhone());
        assertEquals("12345", result.getAddress().zipCode());
        assertEquals("Main St", result.getAddress().name());
        assertEquals("100", result.getAddress().addressNumber());
        assertEquals("Downtown", result.getAddress().neighborhood());
        assertEquals("Metropolis", result.getAddress().city());
        assertEquals("NY", result.getAddress().state());
        assertEquals("Apt 1", result.getAddress().complement());
    }

    @Test
    void toDomainReturnsCustomerWithNullValues() {
        CustomerEntity entity = new CustomerEntity(null, null, null, null, null, null, null, null, null, null, null, null);
        Customer result = customerMapper.toDomain(entity);

        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getEmail());
        assertNull(result.getPhone());
        assertNull(result.getCellPhone());
        assertNull(result.getAddress().zipCode());
        assertNull(result.getAddress().name());
        assertNull(result.getAddress().addressNumber());
        assertNull(result.getAddress().neighborhood());
        assertNull(result.getAddress().city());
        assertNull(result.getAddress().state());
        assertNull(result.getAddress().complement());
    }

    @Test
    void toEntityReturnsCustomerEntityWithCorrectValues() {
        Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", address);
        CustomerEntity result = customerMapper.toEntity(customer);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("123456789", result.getPhone());
        assertEquals("987654321", result.getCellPhone());
        assertEquals("12345", result.getZipCode());
        assertEquals("Main St", result.getAddress());
        assertEquals("100", result.getAddressNumber());
        assertEquals("Downtown", result.getNeighborhood());
        assertEquals("Metropolis", result.getCity());
        assertEquals("NY", result.getState());
        assertEquals("Apt 1", result.getComplement());
    }

    @Test
    void toEntityReturnsCustomerEntityWithNullValues() {
        Address address = new Address(null, null, null, null, null, null, null);
        Customer customer = new Customer(null, null, null, null, null, address);
        CustomerEntity result = customerMapper.toEntity(customer);

        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getEmail());
        assertNull(result.getPhone());
        assertNull(result.getCellPhone());
        assertNull(result.getZipCode());
        assertNull(result.getAddress());
        assertNull(result.getAddressNumber());
        assertNull(result.getNeighborhood());
        assertNull(result.getCity());
        assertNull(result.getState());
        assertNull(result.getComplement());
    }

    @Test
    void toDomainReturnsNullWhenEntityIsNull() {
        CustomerEntity entity = null;
        Customer result = customerMapper.toDomain(entity);

        assertNull(result);
    }

    @Test
    void toEntityReturnsNullWhenCustomerIsNull() {
        Customer customer = null;
        CustomerEntity result = customerMapper.toEntity(customer);

        assertNull(result);
    }
}