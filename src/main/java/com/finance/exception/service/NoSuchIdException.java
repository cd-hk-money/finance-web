package com.finance.exception.service;

public class NoSuchIdException extends Exception {

    private final int ERR_CODE;

    public NoSuchIdException(String message, int errCode) {
        super(message);
        ERR_CODE = errCode;
    }
}
