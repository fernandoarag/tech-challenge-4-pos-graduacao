package br.com.fiap.postech.customers.application.exception.custom;

import br.com.fiap.postech.customers.application.exception.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEmailInvalidExceptionTest {

    @Test
    void shouldReturnCorrectErrorMessage() {
        CustomerEmailInvalidException exception = new CustomerEmailInvalidException();
        assertEquals("Customer email is invalid!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        CustomerEmailInvalidException exception = new CustomerEmailInvalidException();
        assertEquals(ErrorType.INVALID_JSON.name(), exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        CustomerEmailInvalidException exception = new CustomerEmailInvalidException();
        assertEquals(ErrorType.INVALID_JSON.getTitle(), exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatus() {
        CustomerEmailInvalidException exception = new CustomerEmailInvalidException();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatus());
    }
}