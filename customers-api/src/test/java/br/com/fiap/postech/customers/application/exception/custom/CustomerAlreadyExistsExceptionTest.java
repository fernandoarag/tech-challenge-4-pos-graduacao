package br.com.fiap.postech.customers.application.exception.custom;

import br.com.fiap.postech.customers.application.exception.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerAlreadyExistsExceptionTest {

    @Test
    void shouldReturnCorrectErrorMessage() {
        String email = "test@example.com";
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException(email);
        assertEquals("Customer with email: 'test@example.com' already exists!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("test@example.com");
        assertEquals(ErrorType.ALREADY_EXISTS.name(), exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("test@example.com");
        assertEquals(ErrorType.ALREADY_EXISTS.getTitle(), exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatus() {
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("test@example.com");
        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatus());
    }
}