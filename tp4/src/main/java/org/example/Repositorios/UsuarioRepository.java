package org.example.Repositorios;

import org.example.Clases.Libro;
import org.example.Clases.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByName(String name);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
