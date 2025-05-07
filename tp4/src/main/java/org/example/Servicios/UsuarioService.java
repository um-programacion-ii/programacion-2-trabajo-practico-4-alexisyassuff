package org.example.Servicios;

import org.example.Clases.Libro;
import org.example.Clases.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorId(Long id);
    Usuario buscarPorEmail(String email);
    Usuario buscarPorNombre(String nombre);
    List<Usuario> obtenerTodosUsuarios();
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    Libro actualizarUsuario(Long id, Usuario usuario);
}
