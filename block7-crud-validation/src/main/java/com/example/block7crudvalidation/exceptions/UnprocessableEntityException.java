package com.example.block7crudvalidation.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String entity) {
        super("Entity could not be validated: " + entity);
    }
}
