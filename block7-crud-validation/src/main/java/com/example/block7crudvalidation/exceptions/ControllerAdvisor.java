package com.example.block7crudvalidation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Generate a controller to get the ExceptionHandler.
@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    // Throws a new Custom error in response when an EntityNotFoundException occurs with a not found Entity -> 404.
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<CustomError> EntityNotFoundExceptionHandler(RuntimeException mess) {
        CustomError error404 = CustomError.build(404, mess.getMessage());
        log.warn(error404.toString());
        return ResponseEntity.status(404).body(error404);
    }

    // Throws a new Custom error in response when an UnprocessableEntity occurs with a not found Entity -> 422.
    @ExceptionHandler(value = UnprocessableEntityException.class)
    public ResponseEntity<CustomError> UnprocessableEntityExceptionHandler(RuntimeException mess) {
        CustomError error422 = CustomError.build(422, mess.getMessage());
        log.warn(error422.toString());
        return ResponseEntity.status(422).body(error422);
    }

}
