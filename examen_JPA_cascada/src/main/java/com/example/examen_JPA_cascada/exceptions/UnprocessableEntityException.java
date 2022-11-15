package com.example.examen_JPA_cascada.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String entity) {
        super("Entity could not be validated: " + entity);
    }
}
