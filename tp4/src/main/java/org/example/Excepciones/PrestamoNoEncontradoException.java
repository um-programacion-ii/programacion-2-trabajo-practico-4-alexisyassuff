package org.example.Excepciones;

import org.jetbrains.annotations.NotNull;

public class PrestamoNoEncontradoException extends @NotNull RuntimeException {
    public PrestamoNoEncontradoException(Long id) {
        super("Prestamo con ID" + id + "no encontrado: ");
    }
}
