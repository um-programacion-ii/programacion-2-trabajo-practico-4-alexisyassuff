package org.example.TestControladores;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Clases.Libro;
import org.example.Clases.Usuario;
import org.example.Controladores.LibroController;
import org.example.Controladores.UsuarioController;
import org.example.Enum.EstadoLibro;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.Servicios.LibroService;
import org.example.Servicios.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void TestObtenerTodosUsuarios() {
        Usuario usuario1 = new Usuario(1L, "Daniel Gimenez", "DanielGimenez@gmail.com");
        Usuario usuario2 = new Usuario(2L, "Danilo Gomez", "DaniloGomez@gmail.com\"");
        List<Usuario> listaUsuarios = List.of(usuario1, usuario2);

        when(usuarioService.obtenerTodosUsuarios()).thenReturn(listaUsuarios);

        List<Usuario> resultado = usuarioController.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(usuarioService).obtenerTodosUsuarios();
    }

    @Test
    void busquedaIDExiste() {
        Long id = 1L;
        Usuario usuarioEsperado = new Usuario(1L, "Daniel Gimenez", "DanielGimenez@gmail.com");

        when(usuarioService.buscarPorId(id)).thenReturn(usuarioEsperado);

        Usuario resultado = usuarioController.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioService).buscarPorId(id);
    }

    @Test
    void busquedaEmailExiste() {
        String email = "DanielGimenez@gmail.com";
        Usuario usuarioEsperado = new Usuario(1L, "Daniel Gimenez", "DanielGimenez@gmail.com");

        when(usuarioService.buscarPorEmail(email)).thenReturn(usuarioEsperado);

        Usuario resultado = usuarioController.buscarPorEmail(email);

        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
        verify(usuarioService).buscarPorEmail(email);
    }

    @Test
    void busquedaNombreExiste() {
        String nombre = "Daniel Gimenez";
        Usuario usuarioEsperado = new Usuario(1L, "Daniel Gimenez", "DanielGimenez@gmail.com");

        when(usuarioService.buscarPorNombre(nombre)).thenReturn(usuarioEsperado);

        Usuario resultado = usuarioController.buscarPorNombre(nombre);

        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
        verify(usuarioService).buscarPorNombre(nombre);
    }

    @Test
    void TestCrearUsuario() {
        Usuario usuario = new Usuario(1L, "usuario1@example.com", "Usuario Uno");

        when(usuarioService.guardarUsuario(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioController.guardarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
        verify(usuarioService).guardarUsuario(usuario);
    }

    @Test
    void TestActualizarUsuario() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "usuario1@example.com", "Usuario Uno");
        Usuario usuarioActualizado = new Usuario(id, "usuario1@example.com", "Usuario Uno Actualizado");

        when(usuarioService.actualizarUsuario(id, usuario)).thenReturn(usuarioActualizado);

        Usuario resultado = usuarioController.actualizarUsuario(id, usuario);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioService).actualizarUsuario(id, usuario);
    }

}
