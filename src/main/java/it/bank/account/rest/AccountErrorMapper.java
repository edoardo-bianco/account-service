package it.bank.account.rest;

import io.quarkus.runtime.util.ExceptionUtil;
import it.bank.account.domain.exception.AccountAlreadyExistsException;
import it.bank.account.domain.exception.AccountException;
import it.bank.account.domain.exception.AccountNotFoundException;
import it.bank.account.domain.exception.ErrorResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountErrorMapper implements ExceptionMapper<AccountException> {

    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @param exception the exception to map to a response.
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(AccountException exception) {

        Status statusCode = Status.INTERNAL_SERVER_ERROR;
        String message = "An unexpected error occured while processing the request.";

        if (exception instanceof  AccountAlreadyExistsException) {
            statusCode = Status.PAYMENT_REQUIRED;
            message = exception.getMessage();
        } else if (exception instanceof  AccountNotFoundException){
            statusCode = Status.NOT_FOUND;
            message = exception.getMessage();
        }
        ErrorResponse errorResponse = new ErrorResponse(statusCode.getStatusCode(),message, ExceptionUtil.generateStackTrace(exception));

        return Response.status(statusCode).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
