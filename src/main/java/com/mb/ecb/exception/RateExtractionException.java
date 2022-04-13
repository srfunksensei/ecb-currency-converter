package com.mb.ecb.exception;

public class RateExtractionException extends Exception {
    public RateExtractionException(String message) {
        super(message);
    }

    public RateExtractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateExtractionException(Throwable cause) {
        super(cause);
    }
}
