package com.demo.gk0624.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable th) {
        super(th);
    }

    public ApplicationException(String message, Throwable th) {
        super(message,th);
    }

}
