package br.com.fiap.postech.customers.application.exception.custom;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidInputExceptionTest {

    @Test
    void shouldReturnCorrectErrorMessage() {
        InvalidInputException exception = new InvalidInputException("Invalid input provided");
        assertEquals("Invalid input provided", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        InvalidInputException exception = new InvalidInputException("Invalid input provided");
        assertEquals("INVALID_INPUT", exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        InvalidInputException exception = new InvalidInputException("Invalid input provided");
        assertEquals("Invalid Input", exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatus() {
        InvalidInputException exception = new InvalidInputException("Invalid input provided");
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatus());
    }
}