package com.finance.web.exception;

public class NotExistUserException extends RuntimeException {

    public NotExistUserException() {
        super();
    }

    public NotExistUserException(String message) {
        super(message);
    }

    public NotExistUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistUserException(Throwable cause) {
        super(cause);
    }

}
