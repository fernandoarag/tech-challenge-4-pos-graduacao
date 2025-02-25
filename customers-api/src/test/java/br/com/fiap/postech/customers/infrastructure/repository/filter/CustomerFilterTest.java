package br.com.fiap.postech.customers.infrastructure.repository.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerFilterTest {

    @Test
    void customerFilterHandlesValidData() {
        CustomerFilter filter = new CustomerFilter(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", "12345", "Downtown", "Metropolis", "NY");

        assertEquals(1L, filter.getId());
        assertEquals("John Doe", filter.getName());
        assertEquals("john.doe@example.com", filter.getEmail());
        assertEquals("123456789", filter.getPhone());
        assertEquals("987654321", filter.getCellPhone());
        assertEquals("12345", filter.getZipCode());
        assertEquals("Downtown", filter.getNeighborhood());
        assertEquals("Metropolis", filter.getCity());
        assertEquals("NY", filter.getState());
    }

    @Test
    void customerFilterHandlesNullValues() {
        CustomerFilter filter = new CustomerFilter();

        assertNull(filter.getId());
        assertNull(filter.getName());
        assertNull(filter.getEmail());
        assertNull(filter.getPhone());
        assertNull(filter.getCellPhone());
        assertNull(filter.getZipCode());
        assertNull(filter.getNeighborhood());
        assertNull(filter.getCity());
        assertNull(filter.getState());
    }

    @Test
    void customerFilterHandlesEmptyStrings() {
        CustomerFilter filter = new CustomerFilter(null, "", "", "", "", "", "", "", "");

        assertNull(filter.getId());
        assertEquals("", filter.getName());
        assertEquals("", filter.getEmail());
        assertEquals("", filter.getPhone());
        assertEquals("", filter.getCellPhone());
        assertEquals("", filter.getZipCode());
        assertEquals("", filter.getNeighborhood());
        assertEquals("", filter.getCity());
        assertEquals("", filter.getState());
    }

    @Test
    void customerFilterHandlesIdOnlyConstructor() {
        CustomerFilter filter = new CustomerFilter(1L);

        assertEquals(1L, filter.getId());
        assertNull(filter.getName());
        assertNull(filter.getEmail());
        assertNull(filter.getPhone());
        assertNull(filter.getCellPhone());
        assertNull(filter.getZipCode());
        assertNull(filter.getNeighborhood());
        assertNull(filter.getCity());
        assertNull(filter.getState());
    }

    @Test
    void idGetter() {
        CustomerFilter filter = new CustomerFilter(1L);
        assertEquals(1L, filter.getId());
    }

    @Test
    void nameGetter() {
        CustomerFilter filter = new CustomerFilter(null, "John Doe", null, null, null, null, null, null, null);
        assertEquals("John Doe", filter.getName());
    }

    @Test
    void emailGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, "john.doe@example.com", null, null, null, null, null, null);
        assertEquals("john.doe@example.com", filter.getEmail());
    }

    @Test
    void phoneGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, null, "123456789", null, null, null, null, null);
        assertEquals("123456789", filter.getPhone());
    }

    @Test
    void cellPhoneGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, null, null, "987654321", null, null, null, null);
        assertEquals("987654321", filter.getCellPhone());
    }

    @Test
    void zipCodeGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, null, null, null, "12345", null, null, null);
        assertEquals("12345", filter.getZipCode());
    }

    @Test
    void neighborhoodGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, null, null, null, null, "Downtown", null, null);
        assertEquals("Downtown", filter.getNeighborhood());
    }

    @Test
    void cityGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, null, null, null, null, null, "Metropolis", null);
        assertEquals("Metropolis", filter.getCity());
    }

    @Test
    void stateGetter() {
        CustomerFilter filter = new CustomerFilter(null, null, null, null, null, null, null, null, "NY");
        assertEquals("NY", filter.getState());
    }

    @Test
    void idSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setId(1L);
        assertEquals(1L, filter.getId());
    }

    @Test
    void nameSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setName("John Doe");
        assertEquals("John Doe", filter.getName());
    }

    @Test
    void emailSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", filter.getEmail());
    }

    @Test
    void phoneSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setPhone("123456789");
        assertEquals("123456789", filter.getPhone());
    }

    @Test
    void cellPhoneSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setCellPhone("987654321");
        assertEquals("987654321", filter.getCellPhone());
    }

    @Test
    void zipCodeSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setZipCode("12345");
        assertEquals("12345", filter.getZipCode());
    }

    @Test
    void neighborhoodSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setNeighborhood("Downtown");
        assertEquals("Downtown", filter.getNeighborhood());
    }

    @Test
    void citySetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setCity("Metropolis");
        assertEquals("Metropolis", filter.getCity());
    }

    @Test
    void stateSetter() {
        CustomerFilter filter = new CustomerFilter();
        filter.setState("NY");
        assertEquals("NY", filter.getState());
    }

}