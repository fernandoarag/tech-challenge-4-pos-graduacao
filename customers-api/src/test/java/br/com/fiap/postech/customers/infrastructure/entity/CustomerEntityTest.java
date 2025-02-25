package br.com.fiap.postech.customers.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerEntityTest {

    @Test
    void idGetter() {
        CustomerEntity customer = CustomerEntity.builder().id(1L).build();
        assertEquals(1L, customer.getId());
    }

    @Test
    void nameGetter() {
        CustomerEntity customer = CustomerEntity.builder().name("John Doe").build();
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void emailGetter() {
        CustomerEntity customer = CustomerEntity.builder().email("john.doe@example.com").build();
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    void phoneGetter() {
        CustomerEntity customer = CustomerEntity.builder().phone("123456789").build();
        assertEquals("123456789", customer.getPhone());
    }

    @Test
    void cellPhoneGetter() {
        CustomerEntity customer = CustomerEntity.builder().cellPhone("987654321").build();
        assertEquals("987654321", customer.getCellPhone());
    }

    @Test
    void zipCodeGetter() {
        CustomerEntity customer = CustomerEntity.builder().zipCode("12345").build();
        assertEquals("12345", customer.getZipCode());
    }

    @Test
    void addressGetter() {
        CustomerEntity customer = CustomerEntity.builder().address("Main St").build();
        assertEquals("Main St", customer.getAddress());
    }

    @Test
    void addressNumberGetter() {
        CustomerEntity customer = CustomerEntity.builder().addressNumber("100").build();
        assertEquals("100", customer.getAddressNumber());
    }

    @Test
    void neighborhoodGetter() {
        CustomerEntity customer = CustomerEntity.builder().neighborhood("Downtown").build();
        assertEquals("Downtown", customer.getNeighborhood());
    }

    @Test
    void cityGetter() {
        CustomerEntity customer = CustomerEntity.builder().city("Metropolis").build();
        assertEquals("Metropolis", customer.getCity());
    }

    @Test
    void stateGetter() {
        CustomerEntity customer = CustomerEntity.builder().state("NY").build();
        assertEquals("NY", customer.getState());
    }

    @Test
    void complementGetter() {
        CustomerEntity customer = CustomerEntity.builder().complement("Apt 1").build();
        assertEquals("Apt 1", customer.getComplement());
    }

    @Test
    void idSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);
        assertEquals(1L, customer.getId());
    }

    @Test
    void nameSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void emailSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    void phoneSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setPhone("123456789");
        assertEquals("123456789", customer.getPhone());
    }

    @Test
    void cellPhoneSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setCellPhone("987654321");
        assertEquals("987654321", customer.getCellPhone());
    }

    @Test
    void zipCodeSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setZipCode("12345");
        assertEquals("12345", customer.getZipCode());
    }

    @Test
    void addressSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setAddress("Main St");
        assertEquals("Main St", customer.getAddress());
    }

    @Test
    void addressNumberSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setAddressNumber("100");
        assertEquals("100", customer.getAddressNumber());
    }

    @Test
    void neighborhoodSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setNeighborhood("Downtown");
        assertEquals("Downtown", customer.getNeighborhood());
    }

    @Test
    void citySetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setCity("Metropolis");
        assertEquals("Metropolis", customer.getCity());
    }

    @Test
    void stateSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setState("NY");
        assertEquals("NY", customer.getState());
    }

    @Test
    void complementSetter() {
        CustomerEntity customer = new CustomerEntity();
        customer.setComplement("Apt 1");
        assertEquals("Apt 1", customer.getComplement());
    }

    @Test
    void allFieldsNullByDefault() {
        CustomerEntity customer = new CustomerEntity();
        assertNull(customer.getId());
        assertNull(customer.getName());
        assertNull(customer.getEmail());
        assertNull(customer.getPhone());
        assertNull(customer.getCellPhone());
        assertNull(customer.getZipCode());
        assertNull(customer.getAddress());
        assertNull(customer.getAddressNumber());
        assertNull(customer.getNeighborhood());
        assertNull(customer.getCity());
        assertNull(customer.getState());
        assertNull(customer.getComplement());
    }

    @Test
    void emailCannotBeNull() {
        CustomerEntity customer = CustomerEntity.builder().email(null).build();
        assertNull(customer.getEmail());
    }

    @Test
    void emailMustBeUnique() {
        CustomerEntity customer1 = CustomerEntity.builder().email("unique@example.com").build();
        CustomerEntity customer2 = CustomerEntity.builder().email("unique@example.com").build();
        assertEquals("unique@example.com", customer1.getEmail());
        assertEquals("unique@example.com", customer2.getEmail());
    }
}