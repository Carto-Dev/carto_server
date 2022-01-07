package com.carto.server.config;

import com.carto.server.exception.ExceptionResponse;
import com.carto.server.exception.FirebaseException;
import com.carto.server.exception.InternalServerErrorException;
import com.carto.server.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FirebaseException.class)
    public ResponseEntity<ExceptionResponse> handleFirebaseException(FirebaseException firebaseException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(firebaseException.getErrorCode(), firebaseException.getErrorMessage());

        return new ResponseEntity<>(exceptionResponse, CONFLICT);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerErrorException(InternalServerErrorException internalServerErrorException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(internalServerErrorException.getErrorCode(), internalServerErrorException.getErrorMessage());

        return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException notFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(notFoundException.getErrorCode(), notFoundException.getErrorMessage());

        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handle(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" ").append(violation.getMessage()));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(400, errorMessage);

        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

}
