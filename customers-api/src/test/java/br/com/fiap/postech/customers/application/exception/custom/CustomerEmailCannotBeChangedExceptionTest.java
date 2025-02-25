package br.com.fiap.postech.customers.application.exception.custom;

import br.com.fiap.postech.customers.application.exception.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEmailCannotBeChangedExceptionTest {

    @Test
    void shouldReturnCorrectErrorMessage() {
        CustomerEmailCannotBeChangedException exception = new CustomerEmailCannotBeChangedException();
        assertEquals("Customer email cannot be changed!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        CustomerEmailCannotBeChangedException exception = new CustomerEmailCannotBeChangedException();
        assertEquals(ErrorType.EMAIL_CANNOT_BE_CHANGED.name(), exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        CustomerEmailCannotBeChangedException exception = new CustomerEmailCannotBeChangedException();
        assertEquals(ErrorType.EMAIL_CANNOT_BE_CHANGED.getTitle(), exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatus() {
        CustomerEmailCannotBeChangedException exception = new CustomerEmailCannotBeChangedException();
        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatus());
    }
}