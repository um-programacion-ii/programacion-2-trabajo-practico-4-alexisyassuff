package org.example.Excepciones;

import org.jetbrains.annotations.NotNull;

public class UsuarioNoEncontradoException extends @NotNull RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("Usuario con ID" + id + "no encontrado: ");
    }
    public UsuarioNoEncontradoException(String campo) {
        super("Usuario no encontrado: " + campo);
    }
}
