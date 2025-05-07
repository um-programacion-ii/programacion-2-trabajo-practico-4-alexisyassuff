package org.example.Excepciones;

import org.jetbrains.annotations.NotNull;

public class UsuarioNoEncontradoException extends @NotNull RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
    }
    public UsuarioNoEncontradoException(String campo) {
        super("Usuario no encontrado: " + campo);
    }
}
