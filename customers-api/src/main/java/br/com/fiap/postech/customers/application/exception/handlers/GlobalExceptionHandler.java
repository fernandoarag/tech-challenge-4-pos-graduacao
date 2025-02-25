package br.com.fiap.postech.customers.application.exception.handlers;

import br.com.fiap.postech.customers.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.customers.application.exception.custom.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final DatabaseExceptionHandler databaseExceptionHandler;
    private final InterfaceExceptionHandler interfaceExceptionHandler;

    /**
     * Constructs a new GlobalExceptionHandler with the specified handlers.
     *
     * @param databaseExceptionHandler the handler for database-related exceptions
     */
    public GlobalExceptionHandler(DatabaseExceptionHandler databaseExceptionHandler, InterfaceExceptionHandler interfaceExceptionHandler) {
        this.databaseExceptionHandler = databaseExceptionHandler;
        this.interfaceExceptionHandler = interfaceExceptionHandler;
    }

    /**
     * Handles CustomerNotFoundException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ApiErrorResponseImpl handleCustomerNotFoundException(CustomerNotFoundException e) {
        return databaseExceptionHandler.handleCustomerNotFoundException(e);
    }

    /**
     * Handles CustomerAlreadyExistsException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ApiErrorResponseImpl handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return databaseExceptionHandler.handleCustomerAlreadyExistsException(e);
    }

    /**
     * Handles CustomerEmailCannotBeChangedException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(CustomerEmailCannotBeChangedException.class)
    public ApiErrorResponseImpl handleCustomerEmailCannotBeChangedException(CustomerEmailCannotBeChangedException e) {
        return databaseExceptionHandler.handleCustomerEmailCannotBeChangedException(e);
    }

    /**
     * Handles CustomerEmailCannotBeBlankException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(CustomerEmailCannotBeBlankException.class)
    public ApiErrorResponseImpl handleCustomerEmailCannotBeBlankException(CustomerEmailCannotBeBlankException e) {
        return databaseExceptionHandler.handleCustomerEmailCannotBeBlankException(e);
    }

    /**
     * Handles CustomerEmailInvalidException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(CustomerEmailInvalidException.class)
    public ApiErrorResponseImpl handleCustomerEmailInvalidException(CustomerEmailInvalidException e) {
        return databaseExceptionHandler.handleCustomerEmailInvalidException(e);
    }

    /**
     * Handles DataIntegrityViolationException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiErrorResponseImpl handleDatabaseException(DataIntegrityViolationException e) {
        return databaseExceptionHandler.handleDatabaseIntegrityViolation(e);
    }

    /**
     * Handles InvalidInputException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(InvalidInputException.class)
    public ApiErrorResponseImpl handleInterfaceException(InvalidInputException e) {
        return interfaceExceptionHandler.handleInvalidInputException(e);
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponseImpl handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return interfaceExceptionHandler.handleMethodArgumentNotValidException(e);
    }
}