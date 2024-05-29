package io.vehita.exception;

public class DuplicatedUsernameException extends RuntimeException {
    public DuplicatedUsernameException(String username) {
        super("[" + username + "] 는 이미 존재하는 회원입니다.");
    }
}
