package io.vehita.controller;

import io.vehita.domain.dto.ErrorResponseData;
import io.vehita.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseData handleUserNotFoundException(UserNotFoundException e) {
        return new ErrorResponseData(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedUsernameException.class)
    public ErrorResponseData handleDuplicatedUsernameException(DuplicatedUsernameException e) {
        return new ErrorResponseData(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidPasswordException.class)
    public ErrorResponseData handleInvalidPasswordException(InvalidPasswordException e) {
        return new ErrorResponseData(e.getMessage());
    }
}
