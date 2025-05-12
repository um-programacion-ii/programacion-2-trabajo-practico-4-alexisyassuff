package org.example.Controladores;
import org.example.Clases.*;
import org.example.Servicios.LibroService;
import org.example.Servicios.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    // Obtener todos los usuarios del sistema
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodosUsuarios();
    }
    // Buscar un usuario por su ID
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }
    // Buscar un usuario por su email
    @GetMapping("/email/{email}")
    public Usuario buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }
    // Buscar un usuario por su nombre
    @GetMapping("/nombre/{nombre}")
    public Usuario buscarPorNombre(@PathVariable String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }
    // Registrar un nuevo usuario
    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }
    // Actualizar los detalles de un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    // Eliminar un usuario del sistema
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
