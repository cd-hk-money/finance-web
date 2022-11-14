package com.finance.web.exception;

public class RefreshTokenNotValidException extends RuntimeException {

    public RefreshTokenNotValidException() {
        super();
    }

    public RefreshTokenNotValidException(String message) {
        super(message);
    }

    public RefreshTokenNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenNotValidException(Throwable cause) {
        super(cause);
    }
}
