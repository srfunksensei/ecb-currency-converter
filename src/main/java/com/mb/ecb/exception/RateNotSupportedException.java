package com.mb.ecb.exception;

public class RateNotSupportedException extends Exception {
    public RateNotSupportedException(String message) {
        super(message);
    }

    public RateNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateNotSupportedException(Throwable cause) {
        super(cause);
    }
}
