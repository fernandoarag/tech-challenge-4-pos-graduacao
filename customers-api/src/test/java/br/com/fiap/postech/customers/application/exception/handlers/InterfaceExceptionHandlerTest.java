package br.com.fiap.postech.customers.application.exception.handlers;

import br.com.fiap.postech.customers.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.customers.application.exception.ErrorType;
import br.com.fiap.postech.customers.application.exception.custom.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterfaceExceptionHandlerTest {

    private InterfaceExceptionHandler interfaceExceptionHandler;

    @BeforeEach
    void setUp() {
        interfaceExceptionHandler = new InterfaceExceptionHandler();
    }

    @Test
    void shouldHandleInvalidInputException() {
        InvalidInputException exception = new InvalidInputException("Invalid input provided");
        ApiErrorResponseImpl response = interfaceExceptionHandler.handleInvalidInputException(exception);
        assertEquals(ErrorType.INVALID_INPUT.name(), response.getType());
        assertEquals(ErrorType.INVALID_INPUT.getTitle(), response.getTitle());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Invalid input provided", response.getDetail());
    }

    @Test
    void shouldHandleInvalidInputExceptionWithEmptyMessage() {
        InvalidInputException exception = new InvalidInputException("");
        ApiErrorResponseImpl response = interfaceExceptionHandler.handleInvalidInputException(exception);
        assertEquals(ErrorType.INVALID_INPUT.name(), response.getType());
        assertEquals(ErrorType.INVALID_INPUT.getTitle(), response.getTitle());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("", response.getDetail());
    }

    @Test
    void shouldHandleInvalidInputExceptionWithNullMessage() {
        InvalidInputException exception = new InvalidInputException(null);
        ApiErrorResponseImpl response = interfaceExceptionHandler.handleInvalidInputException(exception);
        assertEquals(ErrorType.INVALID_INPUT.name(), response.getType());
        assertEquals(ErrorType.INVALID_INPUT.getTitle(), response.getTitle());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNull(response.getDetail());
    }

    @Test
    void handleMethodArgumentNotValidExceptionReturnsApiErrorResponse() {
        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_INPUT.name(),
                ErrorType.INVALID_INPUT.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed for: field - defaultMessage; "
        );

        ApiErrorResponseImpl response = interfaceExceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), response.getDetail());
    }

    @Test
    void handleMethodArgumentNotValidExceptionWithMultipleFieldErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError1 = new FieldError("objectName", "field1", "defaultMessage1");
        FieldError fieldError2 = new FieldError("objectName", "field2", "defaultMessage2");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_INPUT.name(),
                ErrorType.INVALID_INPUT.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed for: field1 - defaultMessage1; field2 - defaultMessage2; "
        );

        ApiErrorResponseImpl response = interfaceExceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), response.getDetail());
    }

    @Test
    void handleMethodArgumentNotValidExceptionWithNoFieldErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of());
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl(
                ErrorType.INVALID_INPUT.name(),
                ErrorType.INVALID_INPUT.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed for: "
        );

        ApiErrorResponseImpl response = interfaceExceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals(expectedResponse.getType(), response.getType());
        assertEquals(expectedResponse.getTitle(), response.getTitle());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getDetail(), response.getDetail());
    }

}