package org.example.Exception;

public class InvalidAccountType extends RuntimeException {
    public InvalidAccountType(String message) {
        super(message);
    }
}
