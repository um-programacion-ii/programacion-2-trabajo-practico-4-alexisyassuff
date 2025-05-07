package org.example.Servicios;

import org.example.Clases.Libro;

import java.util.List;

public interface LibroService {
    Libro buscarPorIsbn(String isbn);
    List<Libro> obtenerTodosLibros();
    Libro guardarLibro(Libro libro);
    void eliminarLibro(Long id);
    Libro actualizarLibro(Long id, Libro libro);
}
