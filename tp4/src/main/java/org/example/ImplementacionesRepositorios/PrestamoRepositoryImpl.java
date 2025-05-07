package org.example.ImplementacionesRepositorios;

import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Repositorios.PrestamoRepository;

import java.util.*;

public class PrestamoRepositoryImpl implements PrestamoRepository {
    private final Map<Long, Prestamo> prestamos = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setId(nextId++);
        }
        prestamos.put(prestamo.getId(), prestamo);
        return prestamo;
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return Optional.ofNullable(prestamos.get(id));
    }

    @Override
    public Optional<Prestamo> findByUser(Usuario usuario) {
        return Optional.ofNullable(prestamos.get(usuario));
    }

    @Override
    public Optional<Prestamo> findByBook(Libro libro) {
        return Optional.ofNullable(prestamos.get(libro));
    }

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(prestamos.values());
    }

    @Override
    public void deleteById(Long id) {
        prestamos.remove(id);

    }

    @Override
    public boolean existsById(Long id) {
        return prestamos.containsKey(id);
    }


}
