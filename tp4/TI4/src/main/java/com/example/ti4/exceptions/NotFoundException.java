package com.example.ti4.exceptions;

public class NotFoundException extends IllegalArgumentException {
    public NotFoundException(String entityName) {
        super(entityName + " con el id especificado no fue encontrado");
    }
}

