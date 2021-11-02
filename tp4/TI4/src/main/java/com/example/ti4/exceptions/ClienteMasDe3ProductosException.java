package com.example.ti4.exceptions;

public class ClienteMasDe3ProductosException extends IllegalArgumentException {
    public ClienteMasDe3ProductosException() {
        super("El cliente no puede cargar mas de un producto por dia");
    }
}
