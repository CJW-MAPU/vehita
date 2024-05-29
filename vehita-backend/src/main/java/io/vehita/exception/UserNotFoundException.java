package io.vehita.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("[" + username + "] 는 존재하지 않는 회원입니다.");
    }
}
