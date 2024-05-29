package io.vehita.exception;

public class EmptyCookieException extends RuntimeException {
    public EmptyCookieException() {
        super("쿠키가 비어있습니다.");
    }
}
