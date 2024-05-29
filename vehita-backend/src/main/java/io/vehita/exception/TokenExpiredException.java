package io.vehita.exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String token) {
        super("[" + token + "] 이 만료되었습니다.");
    }
}
