package com.carto.server.config;

import com.carto.server.exception.ExceptionResponse;
import com.carto.server.exception.FirebaseException;
import com.carto.server.exception.InternalServerErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FirebaseException.class)
    public ResponseEntity<ExceptionResponse> handleFirebaseException(FirebaseException firebaseException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(firebaseException.getErrorCode(), firebaseException.getErrorMessage());

        return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerErrorException(InternalServerErrorException internalServerErrorException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(internalServerErrorException.getErrorCode(), internalServerErrorException.getErrorMessage());

        return new ResponseEntity<>(exceptionResponse, INTERNAL_SERVER_ERROR);
    }

}
