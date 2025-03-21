package com.example.odontogenda.exceptions;

public class InvalidUserPrefixException extends RuntimeException {
    private final String tipo; // "cliente" ou "dentista"

    public InvalidUserPrefixException(String message, String tipo) {
        super(message);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
