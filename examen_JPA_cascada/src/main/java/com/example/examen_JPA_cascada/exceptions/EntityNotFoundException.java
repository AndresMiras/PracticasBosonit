package com.example.examen_JPA_cascada.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super("Error EntityNotFoundException... : " + message);
    }
}
