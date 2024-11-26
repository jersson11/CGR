package com.test.testactivedirectory.infrastructure.exception.customException;

public class InvalidVerificationTokenException extends RuntimeException {
    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
