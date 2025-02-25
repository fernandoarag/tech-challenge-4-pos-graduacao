package br.com.fiap.postech.customers.application.exception.custom;

import br.com.fiap.postech.customers.application.exception.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNotFoundExceptionTest {

    @Test
    void shouldReturnCorrectErrorMessage() {
        CustomerNotFoundException exception = new CustomerNotFoundException("ID", "123");
        assertEquals("Customer with ID: '123' not found!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        CustomerNotFoundException exception = new CustomerNotFoundException("ID", "123");
        assertEquals(ErrorType.NOT_FOUND.name(), exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        CustomerNotFoundException exception = new CustomerNotFoundException("ID", "123");
        assertEquals(ErrorType.NOT_FOUND.getTitle(), exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatus() {
        CustomerNotFoundException exception = new CustomerNotFoundException("ID", "123");
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());
    }
}