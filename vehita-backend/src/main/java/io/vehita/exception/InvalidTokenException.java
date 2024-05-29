package io.vehita.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String token) {
        super(token);
    }
}
