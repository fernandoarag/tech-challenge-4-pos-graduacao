package br.com.fiap.postech.customers.application.exception.custom;

import br.com.fiap.postech.customers.application.exception.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEmailCannotBeBlankExceptionTest {

    @Test
    void shouldReturnCorrectErrorMessage() {
        CustomerEmailCannotBeBlankException exception = new CustomerEmailCannotBeBlankException();
        assertEquals("Customer email cannot be blank!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        CustomerEmailCannotBeBlankException exception = new CustomerEmailCannotBeBlankException();
        assertEquals(ErrorType.INVALID_JSON.name(), exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        CustomerEmailCannotBeBlankException exception = new CustomerEmailCannotBeBlankException();
        assertEquals(ErrorType.INVALID_JSON.getTitle(), exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatus() {
        CustomerEmailCannotBeBlankException exception = new CustomerEmailCannotBeBlankException();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatus());
    }
}