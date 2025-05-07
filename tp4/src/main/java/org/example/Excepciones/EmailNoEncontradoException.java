package org.example.Excepciones;

import org.jetbrains.annotations.NotNull;

public class EmailNoEncontradoException extends @NotNull RuntimeException {
    public EmailNoEncontradoException(String email) {
    }
}
