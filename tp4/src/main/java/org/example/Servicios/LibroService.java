package org.example.Servicios;

import org.example.Clases.Libro;
import org.example.Clases.Usuario;

import java.util.List;

public interface LibroService {
    Libro buscarPorId(Long id);
    Libro buscarPorIsbn(String isbn);
    List<Libro> obtenerTodosLibros();
    Libro guardarLibro(Libro libro);
    void eliminarLibro(Long id);
    Libro actualizarLibro(Long id, Libro libro);
}
