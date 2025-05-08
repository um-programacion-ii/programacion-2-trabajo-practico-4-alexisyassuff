package org.example.TestService;
import org.example.Clases.Libro;
import org.example.Clases.Usuario;
import org.example.Enum.EstadoLibro;
import org.example.ImplementacionesServicios.LibroServiceImpl;
import org.example.ImplementacionesServicios.UsuarioServiceImpl;
import org.example.Repositorios.LibroRepository;
import org.example.Repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    @Test
    void testeoBusquedaNombre() {
        // Arrange
        String nombre = "Daniel de Rossi";
        Usuario usuarioEsperado = new Usuario(1L, nombre, "daniel@gmail.com");
        when(usuarioRepository.findByName(nombre)).thenReturn(Optional.of(usuarioEsperado));

        // Act
        Usuario resultado = usuarioService.buscarPorNombre(nombre);

        // Assert
        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
        verify(usuarioRepository).findByName(nombre);
    }
    @Test
    void testeoBusquedaEmail() {
        // Arrange
        String email = "Daniel@gmail.com";
        Usuario usuarioEsperado = new Usuario(1L, "Daniel de Rossi", email);
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioEsperado));

        // Act
        Usuario resultado = usuarioService.buscarPorEmail(email);

        // Assert
        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void testeoTraerTodosUsuariosTamano() {
        // Arrange
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Usuario Usuario2 = new Usuario(2L, "Rocio Lopez", "rocio@gmail.com");
        List<Usuario> usuariosEsperados = List.of(Usuario1, Usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuariosEsperados);

        // Act
        List<Usuario> resultado = usuarioService.obtenerTodosUsuarios();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void testeoTraerTodosUsuariosObjetos() {
        // Arrange
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Usuario Usuario2 = new Usuario(2L, "Rocio Lopez", "rocio@gmail.com");
        List<Usuario> usuariosEsperados = List.of(Usuario1, Usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuariosEsperados);

        // Act
        List<Usuario> resultado = usuarioService.obtenerTodosUsuarios();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.contains(Usuario1));
        assertTrue(resultado.contains(Usuario2));
    }

    @Test
    void testeoEliminarUsuario() {
        // Arrange
        Long id1 = 1L;
        Long id2 = 2L;
        Usuario Usuario1 = new Usuario(id1, "Daniel de Rossi", "daniel@gmail.com");
        Usuario Usuario2 = new Usuario(id2, "Rocio Lopez", "rocio@gmail.com");
        List<Usuario> listaUsuarios = List.of(Usuario1, Usuario2);
        List<Usuario> resultadoEsperado = List.of(Usuario2);
        doNothing().when(usuarioRepository).deleteById(id1);
        when(usuarioRepository.findAll()).thenReturn(resultadoEsperado);

        // Act
        List<Usuario> resultado = usuarioService.eliminarUsuario(id1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertFalse(resultado.contains(Usuario1));
        assertTrue(resultado.contains(Usuario2));
    }

    @Test
    void testActualizarUsuario() {
        // Arrange
        Long id = 1L;
        Usuario Usuario1 = new Usuario(id, "Daniel de Rossi", "daniel@gmail.com");

        Usuario usuarioActualizado = new Usuario(id, "Daniel de Rossi", "daniel123@gmail.com");

        when(usuarioRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioActualizado);

        // Act
        Usuario resultado = usuarioService.actualizarUsuario(id, Usuario1);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioRepository).existsById(id);
        verify(usuarioRepository).save(Usuario1);
    }
    @Test
    void testGuardarUsuario() {
        // Arrange
        Long id = 1L;
        Usuario Usuario1 = new Usuario(id, "Daniel de Rossi", "daniel@gmail.com");

        when(usuarioRepository.save(Usuario1)).thenReturn(Usuario1);

        // Act
        Usuario resultado = usuarioService.guardarUsuario(Usuario1);

        // Assert
        assertNotNull(resultado);
        assertEquals(Usuario1, resultado);
        verify(usuarioRepository).save(Usuario1);
    }
}
