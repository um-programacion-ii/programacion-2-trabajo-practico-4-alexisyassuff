package org.example.Repositorios;

import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findByUser(Usuario usuario);
    Optional<Prestamo> findByBook(Libro libro);
    List<Prestamo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
