package org.example.Excepciones;

public class LibroNoEncontradoExceptionISBN extends @org.jetbrains.annotations.NotNull RuntimeException {
    public LibroNoEncontradoExceptionISBN(String isbn) {
        super("Libro con isbn" + isbn + "no encontrado: ");

    }
}
