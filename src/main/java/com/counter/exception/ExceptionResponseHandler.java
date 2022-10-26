package com.counter.exception;

import com.counter.controller.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(ApplicationCounterNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleCounterNotFoundException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ApplicationCounterException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleApplicationException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }
}
