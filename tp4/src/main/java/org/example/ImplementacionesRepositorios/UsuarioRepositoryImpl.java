package org.example.ImplementacionesRepositorios;

import org.example.Clases.Libro;
import org.example.Clases.Usuario;
import org.example.Repositorios.UsuarioRepository;

import java.util.*;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(nextId++);
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> findByName(String name) {
        return Optional.ofNullable(usuarios.get(name));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return Optional.ofNullable(usuarios.get(email));
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void deleteById(Long id) {
        usuarios.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return usuarios.containsKey(id);
    }

}
