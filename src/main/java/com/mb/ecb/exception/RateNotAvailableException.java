package com.mb.ecb.exception;

public class RateNotAvailableException extends Exception {
    public RateNotAvailableException(String message) {
        super(message);
    }

    public RateNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateNotAvailableException(Throwable cause) {
        super(cause);
    }
}
