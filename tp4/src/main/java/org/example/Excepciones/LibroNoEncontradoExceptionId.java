package org.example.Excepciones;

public class LibroNoEncontradoExceptionId extends @org.jetbrains.annotations.NotNull RuntimeException {
    public LibroNoEncontradoExceptionId(Long id) {
        super("Libro con ID" + id + "no encontrado: ");
    }
}
