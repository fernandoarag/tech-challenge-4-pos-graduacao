package br.com.fiap.postech.customers.application.exception.handlers;

import br.com.fiap.postech.customers.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.customers.application.exception.ErrorType;
import br.com.fiap.postech.customers.application.exception.custom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private DatabaseExceptionHandler databaseExceptionHandler;
    private GlobalExceptionHandler globalExceptionHandler;
    private InterfaceExceptionHandler interfaceExceptionHandler;

    @BeforeEach
    void setUp() {
        interfaceExceptionHandler = mock(InterfaceExceptionHandler.class);
        databaseExceptionHandler = mock(DatabaseExceptionHandler.class);
        globalExceptionHandler = new GlobalExceptionHandler(databaseExceptionHandler, interfaceExceptionHandler);
    }

    @Test
    void shouldHandleCustomerNotFoundException() {
        CustomerNotFoundException exception = new CustomerNotFoundException("ID", "123");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.NOT_FOUND.name(),
                ErrorType.NOT_FOUND.getTitle(),
                HttpStatus.NOT_FOUND.value(),
                "Customer with ID: '123' not found!"
        );

        when(databaseExceptionHandler.handleCustomerNotFoundException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleCustomerNotFoundException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void shouldHandleCustomerAlreadyExistsException() {
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("email@email.com");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.EMAIL_CANNOT_BE_CHANGED.name(),
                ErrorType.EMAIL_CANNOT_BE_CHANGED.getTitle(),
                HttpStatus.CONFLICT.value(),
                "Customer with email: 'email@email.com' already exists!"
        );

        when(databaseExceptionHandler.handleCustomerAlreadyExistsException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleCustomerAlreadyExistsException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void shouldHandleCustomerEmailCannotBeChangedException() {
        CustomerEmailCannotBeChangedException exception = new CustomerEmailCannotBeChangedException();
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.EMAIL_CANNOT_BE_CHANGED.name(),
                ErrorType.EMAIL_CANNOT_BE_CHANGED.getTitle(),
                HttpStatus.CONFLICT.value(),
                "Customer email cannot be changed!"
        );

        when(databaseExceptionHandler.handleCustomerEmailCannotBeChangedException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleCustomerEmailCannotBeChangedException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void shouldHandleCustomerEmailCannotBeBlankException() {
        CustomerEmailCannotBeBlankException exception = new CustomerEmailCannotBeBlankException();
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_JSON.name(),
                ErrorType.INVALID_JSON.getTitle(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Customer email cannot be blank!"
        );

        when(databaseExceptionHandler.handleCustomerEmailCannotBeBlankException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleCustomerEmailCannotBeBlankException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void shouldHandleCustomerEmailInvalidException() {
        CustomerEmailInvalidException exception = new CustomerEmailInvalidException();
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_JSON.name(),
                ErrorType.INVALID_JSON.getTitle(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Customer email is invalid!"
        );

        when(databaseExceptionHandler.handleCustomerEmailInvalidException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleCustomerEmailInvalidException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void shouldHandleDatabaseException() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("DataIntegrityViolationException");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INTERNAL_SERVER_ERROR.name(),
                ErrorType.INTERNAL_SERVER_ERROR.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                "DataIntegrityViolationException"
        );

        when(globalExceptionHandler.handleDatabaseException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleDatabaseException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void shouldHandleInvalidInputException() {
        InvalidInputException exception = new InvalidInputException("invalid_input");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_INPUT.name(),
                ErrorType.INVALID_INPUT.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                "invalid_input"
        );

        when(globalExceptionHandler.handleInterfaceException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleInterfaceException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), exception.getMessage());
    }

    @Test
    void handleMethodArgumentNotValidExceptionReturnsApiErrorResponse() {
        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_JSON.name(),
                ErrorType.INVALID_JSON.getTitle(),
                400,
                "Validation failed"
        );

        when(interfaceExceptionHandler.handleMethodArgumentNotValidException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleMethodArgumentNotValidException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), response.getDetail());
    }

    @Test
    void handleMethodArgumentNotValidExceptionWithFieldErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_JSON.name(),
                ErrorType.INVALID_JSON.getTitle(),
                400,
                "Validation failed for field: field, defaultMessage"
        );

        when(interfaceExceptionHandler.handleMethodArgumentNotValidException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleMethodArgumentNotValidException(exception);
        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), response.getDetail());
    }

}