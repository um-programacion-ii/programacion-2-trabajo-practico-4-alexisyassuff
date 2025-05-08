package org.example.ImplementacionesServicios;

import org.example.Clases.Libro;
import org.example.Clases.Usuario;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.Excepciones.LibroNoEncontradoExceptionId;
import org.example.Excepciones.UsuarioNoEncontradoException;
import org.example.Repositorios.LibroRepository;
import org.example.Repositorios.UsuarioRepository;
import org.example.Servicios.UsuarioService;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException(email));    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepository.findByName(nombre)
                .orElseThrow(() -> new UsuarioNoEncontradoException(nombre));      }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new LibroNoEncontradoExceptionId(id);
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);     }

}
