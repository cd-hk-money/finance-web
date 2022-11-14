package com.finance.web.exception;

public class AccessTokenNotValidException extends RuntimeException {

    public AccessTokenNotValidException() {
        super();
    }

    public AccessTokenNotValidException(String message) {
        super(message);
    }

    public AccessTokenNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenNotValidException(Throwable cause) {
        super(cause);
    }

    public AccessTokenNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
