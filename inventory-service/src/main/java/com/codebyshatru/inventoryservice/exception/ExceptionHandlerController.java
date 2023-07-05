package com.codebyshatru.inventoryservice.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {
        String errorMessage = getConstraintViolationErrorMessage(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = getConstraintViolationErrorMessage(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    private String getConstraintViolationErrorMessage(RuntimeException ex) {
        System.out.println("Exception-------"+ex.getMessage());
        String message = ex.getMessage();
        // Extract the constraint name from the exception message
        String constraintName = message.substring(message.indexOf("constraint [") + 22, message.length() - 1);
        return "Constraint violation occurred. Field: " + constraintName;
    }

    private String extractConstraintFieldName(RuntimeException ex) {
        if (ex instanceof DataIntegrityViolationException) {
            Throwable cause = ex.getCause();
            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) cause;
                ConstraintViolation<?> violation = constraintViolationException.getConstraintViolations().iterator().next();
                return violation.getPropertyPath().toString();
            }
        } else if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex;
            ConstraintViolation<?> violation = constraintViolationException.getConstraintViolations().iterator().next();
            return violation.getPropertyPath().toString();
        }
        return "N/A";
    }

    // Add more exception handlers for other custom exceptions or specific scenarios if needed

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String errorMessage = "An error occurred.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
