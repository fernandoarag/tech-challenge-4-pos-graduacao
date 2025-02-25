package br.com.fiap.postech.customers.application.exception.custom;

import br.com.fiap.postech.customers.application.exception.ApiErrorResponse;
import br.com.fiap.postech.customers.application.exception.ErrorType;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a customer's email is invalid.
 */
public class CustomerEmailInvalidException extends RuntimeException implements ApiErrorResponse {

    private static final ErrorType type = ErrorType.INVALID_JSON;
    private static final HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

    /**
     * Constructs a new CustomerEmailInvalidException with a default message.
     */
    public CustomerEmailInvalidException() {
        super("Customer email is invalid!");
    }

    /**
     * Returns the type of error.
     *
     * @return the error type as a string
     */
    @Override
    public String getType() {
        return type.name();
    }

    /**
     * Returns the title of the error.
     *
     * @return the error title as a string
     */
    @Override
    public String getTitle() {
        return type.getTitle();
    }

    /**
     * Returns the HTTP status code for the error.
     *
     * @return the HTTP status code as an integer
     */
    @Override
    public int getStatus() {
        return status.value();
    }
}