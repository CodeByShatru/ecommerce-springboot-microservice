package com.codebyshatru.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ImachineMartExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ResourceAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.CONFLICT.value());
        response.setStatus(HttpStatus.CONFLICT);
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDate.now());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ImachineMartCustomException.class)
    public ResponseEntity<ExceptionResponse> customException(ImachineMartCustomException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage(ex.getMessage());

        response.setTimestamp(LocalDate.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedException(UnauthorizedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.UNAUTHORIZED.value());
        response.setStatus(HttpStatus.UNAUTHORIZED);
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDate.now());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}