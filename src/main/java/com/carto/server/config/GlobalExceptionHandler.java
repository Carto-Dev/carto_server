package com.carto.server.config;

import com.carto.server.exception.ExceptionResponse;
import com.carto.server.exception.FirebaseException;
import com.carto.server.exception.InternalServerErrorException;
import com.carto.server.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
