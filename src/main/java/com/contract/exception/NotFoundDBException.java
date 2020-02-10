package com.contract.exception;

public class NotFoundDBException extends RuntimeException {

    public NotFoundDBException(String message) {
        super(message);
    }

    public NotFoundDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundDBException(Throwable cause) {
        super(cause);
    }
}
