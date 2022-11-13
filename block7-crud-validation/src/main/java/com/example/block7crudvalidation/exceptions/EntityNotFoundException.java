package com.example.block7crudvalidation.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super("Error EntityNotFoundException... : " + message);
    }
}
