package org.example.ImplementacionesServicios;
import org.example.Clases.*;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.Excepciones.LibroNoEncontradoExceptionId;
import org.example.Repositorios.*;
import org.example.Servicios.LibroService;

import java.util.List;

public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoExceptionId(id));
    }

    @Override
    public List<Libro> obtenerTodosLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoExceptionId(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoExceptionISBN(isbn));
    }

}
