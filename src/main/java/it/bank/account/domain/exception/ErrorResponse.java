package it.bank.account.domain.exception;

import java.io.Serializable;

/**
 * hold the error info
 */
public class ErrorResponse implements Serializable {

    private static final long SerialVersionUID = 1L;

    private int codStatus;
    private String message;
    private String details;
    private String stackTrace;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(int codStatus, String message, String stackTrace) {
        this.codStatus = codStatus;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public ErrorResponse(int codStatus, String message, String details, String stackTrace) {
        this.codStatus = codStatus;
        this.message = message;
        this.details = details;
        this.stackTrace = stackTrace;
    }

    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
