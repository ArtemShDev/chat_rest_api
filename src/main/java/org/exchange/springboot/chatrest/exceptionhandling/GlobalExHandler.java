package org.exchange.springboot.chatrest.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handleException(
            NoSuchElementException exception) {
        return new ResponseEntity<>(new ExceptionInfo(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handleException(
            MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ExceptionInfo(exception.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handleException(
            Exception exception) {
        return new ResponseEntity<>(new ExceptionInfo(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
