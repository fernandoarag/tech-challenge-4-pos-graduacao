package br.com.fiap.postech.customers.application.factory;

import br.com.fiap.postech.customers.domain.model.Address;
import br.com.fiap.postech.customers.domain.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerFactoryImplTest {
    @Test
    void shouldCreateCustomerWithId() {
        final Address address = new Address("12345", "Main St", "100", "Downtown", "City", "State", "Apt 1");
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", address);
        assertEquals(1L, customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("123456789", customer.getPhone());
        assertEquals("987654321", customer.getCellPhone());
        assertEquals("12345", customer.getAddress().zipCode());
        assertEquals("Main St", customer.getAddress().name());
        assertEquals("100", customer.getAddress().addressNumber());
        assertEquals("Downtown", customer.getAddress().neighborhood());
        assertEquals("City", customer.getAddress().city());
        assertEquals("State", customer.getAddress().state());
        assertEquals("Apt 1", customer.getAddress().complement());
    }

    @Test
    void shouldCreateCustomerWithoutId() {
        final Address address = new Address("12345", "Main St", "100", "Downtown", "City", "State", "Apt 1");
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "987654321", address);
        assertNull(customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("123456789", customer.getPhone());
        assertEquals("987654321", customer.getCellPhone());
        assertEquals("12345", customer.getAddress().zipCode());
        assertEquals("Main St", customer.getAddress().name());
        assertEquals("100", customer.getAddress().addressNumber());
        assertEquals("Downtown", customer.getAddress().neighborhood());
        assertEquals("City", customer.getAddress().city());
        assertEquals("State", customer.getAddress().state());
        assertEquals("Apt 1", customer.getAddress().complement());
    }

    @Test
    void shouldHandleNullValues() {
        final Address address = new Address(null, null, null, null, null, null, null);
        Customer customer = new Customer(null, null, null, null, null, address);
        assertNull(customer.getId());
        assertNull(customer.getName());
        assertNull(customer.getEmail());
        assertNull(customer.getPhone());
        assertNull(customer.getCellPhone());
        assertNull(customer.getAddress().zipCode());
        assertNull(customer.getAddress().name());
        assertNull(customer.getAddress().addressNumber());
        assertNull(customer.getAddress().neighborhood());
        assertNull(customer.getAddress().city());
        assertNull(customer.getAddress().state());
        assertNull(customer.getAddress().complement());
    }

    @Test
    void shouldHandleEmptyStrings() {
        Customer customer = new Customer(null, "", "", "", "", new Address("", "", "", "", "", "", ""));
        assertNull(customer.getId());
        assertEquals("", customer.getName());
        assertEquals("", customer.getEmail());
        assertEquals("", customer.getPhone());
        assertEquals("", customer.getCellPhone());
        assertEquals("", customer.getAddress().zipCode());
        assertEquals("", customer.getAddress().name());
        assertEquals("", customer.getAddress().addressNumber());
        assertEquals("", customer.getAddress().neighborhood());
        assertEquals("", customer.getAddress().city());
        assertEquals("", customer.getAddress().state());
        assertEquals("", customer.getAddress().complement());
    }
}