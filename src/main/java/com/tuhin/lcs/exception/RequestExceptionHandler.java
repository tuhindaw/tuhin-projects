package com.tuhin.lcs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = BadLCSRequestException.class)
    public ResponseEntity<Object> exception(BadLCSRequestException
                                                    exception) {
        return new ResponseEntity<>("Bad request exception", HttpStatus.BAD_REQUEST);
    }
}