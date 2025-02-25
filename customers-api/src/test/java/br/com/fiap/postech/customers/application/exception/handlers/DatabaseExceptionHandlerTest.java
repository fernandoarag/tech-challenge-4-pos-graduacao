package br.com.fiap.postech.customers.application.exception.handlers;

import br.com.fiap.postech.customers.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.customers.application.exception.ErrorType;
import br.com.fiap.postech.customers.application.exception.custom.*;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseExceptionHandlerTest {

    private final DatabaseExceptionHandler handler = new DatabaseExceptionHandler();

    @Test
    void shouldHandleCustomerNotFoundException() {
        CustomerNotFoundException exception = new CustomerNotFoundException("ID", "123");
        ApiErrorResponseImpl response = handler.handleCustomerNotFoundException(exception);
        assertEquals(ErrorType.NOT_FOUND.name(), response.getType());
        assertEquals(ErrorType.NOT_FOUND.getTitle(), response.getTitle());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Customer with ID: '123' not found!", exception.getMessage());
    }

    @Test
    void shouldHandleCustomerAlreadyExistsException() {
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("mockemail@email.com");
        ApiErrorResponseImpl response = handler.handleCustomerAlreadyExistsException(exception);
        assertEquals(ErrorType.EMAIL_CANNOT_BE_CHANGED.name(), response.getType());
        assertEquals(ErrorType.EMAIL_CANNOT_BE_CHANGED.getTitle(), response.getTitle());
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        assertEquals("Customer with email: 'mockemail@email.com' already exists!", exception.getMessage());
    }

    @Test
    void shouldHandleCustomerEmailCannotBeBlankException() {
        CustomerEmailCannotBeBlankException exception = new CustomerEmailCannotBeBlankException();
        ApiErrorResponseImpl response = handler.handleCustomerEmailCannotBeBlankException(exception);
        assertEquals(ErrorType.INVALID_JSON.name(), response.getType());
        assertEquals(ErrorType.INVALID_JSON.getTitle(), response.getTitle());
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus());
        assertEquals("Customer email cannot be blank!", exception.getMessage());
    }

    @Test
    void shouldHandleCustomerEmailCannotBeChangedException() {
        CustomerEmailCannotBeChangedException exception = new CustomerEmailCannotBeChangedException();
        ApiErrorResponseImpl response = handler.handleCustomerEmailCannotBeChangedException(exception);
        assertEquals(ErrorType.EMAIL_CANNOT_BE_CHANGED.name(), response.getType());
        assertEquals(ErrorType.EMAIL_CANNOT_BE_CHANGED.getTitle(), response.getTitle());
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        assertEquals("Customer email cannot be changed!", exception.getMessage());
    }

    @Test
    void shouldHandleCustomerEmailInvalidException() {
        CustomerEmailInvalidException exception = new CustomerEmailInvalidException();
        ApiErrorResponseImpl response = handler.handleCustomerEmailInvalidException(exception);
        assertEquals(ErrorType.INVALID_JSON.name(), response.getType());
        assertEquals(ErrorType.INVALID_JSON.getTitle(), response.getTitle());
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus());
        assertEquals("Customer email is invalid!", exception.getMessage());
    }

    @Test
    void shouldHandleDatabaseIntegrityViolation() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Integrity violation");
        ApiErrorResponseImpl response = handler.handleDatabaseIntegrityViolation(exception);
        assertEquals(ErrorType.INTERNAL_SERVER_ERROR.name(), response.getType());
        assertEquals(ErrorType.INTERNAL_SERVER_ERROR.getTitle(), response.getTitle());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Integrity violation", exception.getMessage());
    }
}