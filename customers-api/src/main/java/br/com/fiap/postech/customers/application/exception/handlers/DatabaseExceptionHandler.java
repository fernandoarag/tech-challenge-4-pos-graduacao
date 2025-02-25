package br.com.fiap.postech.customers.application.exception.handlers;

import br.com.fiap.postech.customers.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.customers.application.exception.ErrorType;
import br.com.fiap.postech.customers.application.exception.custom.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Handler for database-related exceptions.
 */
@Component
public class DatabaseExceptionHandler {

    private static final ErrorType NOT_FOUND = ErrorType.NOT_FOUND;
    private static final ErrorType INVALID_JSON = ErrorType.INVALID_JSON;
    private static final ErrorType INTERNAL_SERVER_ERROR = ErrorType.INTERNAL_SERVER_ERROR;
    private static final ErrorType EMAIL_CANNOT_BE_CHANGED = ErrorType.EMAIL_CANNOT_BE_CHANGED;
    private static final HttpStatus NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;
    private static final HttpStatus CONFLICT_STATUS = HttpStatus.CONFLICT;
    private static final HttpStatus NOT_ACCEPTABLE_STATUS = HttpStatus.NOT_ACCEPTABLE;
    private static final HttpStatus BAD_REQUEST_STATUS = HttpStatus.BAD_REQUEST;

    /**
     * Handles CustomerNotFoundException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleCustomerNotFoundException(CustomerNotFoundException e) {
        return new ApiErrorResponseImpl(
                NOT_FOUND.name(),
                NOT_FOUND.getTitle(),
                NOT_FOUND_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles CustomerAlreadyExistsException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return new ApiErrorResponseImpl(
                EMAIL_CANNOT_BE_CHANGED.name(),
                EMAIL_CANNOT_BE_CHANGED.getTitle(),
                CONFLICT_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles CustomerEmailCannotBeBlankException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleCustomerEmailCannotBeBlankException(CustomerEmailCannotBeBlankException e) {
        return new ApiErrorResponseImpl(
                INVALID_JSON.name(),
                INVALID_JSON.getTitle(),
                NOT_ACCEPTABLE_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles CustomerEmailCannotBeChangedException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleCustomerEmailCannotBeChangedException(CustomerEmailCannotBeChangedException e) {
        return new ApiErrorResponseImpl(
                EMAIL_CANNOT_BE_CHANGED.name(),
                EMAIL_CANNOT_BE_CHANGED.getTitle(),
                CONFLICT_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles CustomerEmailInvalidException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleCustomerEmailInvalidException(CustomerEmailInvalidException e) {
        return new ApiErrorResponseImpl(
                INVALID_JSON.name(),
                INVALID_JSON.getTitle(),
                NOT_ACCEPTABLE_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles DataIntegrityViolationException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleDatabaseIntegrityViolation(DataIntegrityViolationException e) {
        return new ApiErrorResponseImpl(
                INTERNAL_SERVER_ERROR.name(),
                INTERNAL_SERVER_ERROR.getTitle(),
                BAD_REQUEST_STATUS.value(),
                e.getMessage()
        );
    }
}