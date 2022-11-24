package com.example.block7crudvalidation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


// Generate a controller to get the ExceptionHandler.
@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    // Throws a new Custom error in response when an EntityNotFoundException occurs with a not found Entity -> 404.
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public Object EntityNotFoundExceptionHandler(RuntimeException mess) {
        CustomError error404 = CustomError.build(404, mess.getMessage());
        log.warn(error404.toString());
        return error404;
    }

    // Throws a new Custom error in response when an UnprocessableEntity occurs with a not found Entity -> 422.
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = UnprocessableEntityException.class)
    public Object UnprocessableEntityExceptionHandler(RuntimeException mess) {
        CustomError error422 = CustomError.build(422, mess.getMessage());
        log.warn(error422.toString());
        return error422;
    }

    // Throws a new Custom error in response when an IllegalStateException occurs with a not found Entity -> 422.
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = IllegalStateException.class)
    public Object IllegalStateException(RuntimeException mess) {
        CustomError error422 = CustomError.build(422, mess.getMessage());
        log.warn(error422.toString());
        return error422;
    }



}
