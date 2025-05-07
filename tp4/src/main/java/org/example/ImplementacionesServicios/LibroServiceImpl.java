package org.example.Implementaciones;
import org.example.Enum.*;
import org.example.Clases.*;
import org.example.Servicios.*;
import org.example.Repositorios.*;
import org.example.Implementaciones.*;
import org.example.Enum.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LibroServiceImpl {
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }
}
