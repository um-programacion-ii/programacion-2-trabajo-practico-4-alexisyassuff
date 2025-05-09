package org.example.TestRepositorios;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Enum.EstadoLibro;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.ImplementacionesRepositorios.LibroRepositoryImpl;
import org.example.ImplementacionesRepositorios.UsuarioRepositoryImpl;
import org.example.ImplementacionesServicios.*;
import org.example.Repositorios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UsuarioRepositoryImplTest {
    private UsuarioRepositoryImpl usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
    }


    @Test
    void busquedaIdExiste() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Ana", "ana@mail.com"));
        Optional<Usuario> encontrado = usuarioRepository.findById(usuario.getId());

        assertTrue(encontrado.isPresent());
        assertEquals(usuario.getId(), encontrado.get().getId());
    }

    @Test
    void busquedaIdNOExiste() {
        Optional<Usuario> resultado = usuarioRepository.findById(100L);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void busquedaNombreExiste() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Carlos Perez", "carlos@mail.com"));
        Optional<Usuario> resultado = usuarioRepository.findByName("Carlos Perez");
        assertTrue(resultado.isPresent());
        assertEquals(usuario.getNombre(), resultado.get().getNombre());
    }

    @Test
    void busquedaNombreNOExiste() {
        Optional<Usuario> resultado = usuarioRepository.findByName("Desconocido");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void busquedaEmailExiste() {
        usuarioRepository.save(new Usuario(1L, "Laura", "laura@mail.com"));
        Optional<Usuario> resultado = usuarioRepository.findByEmail("laura@mail.com");

        assertTrue(resultado.isPresent());
        assertEquals("laura@mail.com", resultado.get().getEmail());
    }

    @Test
    void busquedaEmailNOExiste() {
        Optional<Usuario> resultado = usuarioRepository.findByEmail("otro@mail.com");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void TestObtenerTodosUsuarios() {
        usuarioRepository.save(new Usuario(null, "Uno", "uno@mail.com"));
        usuarioRepository.save(new Usuario(null, "Dos", "dos@mail.com"));

        List<Usuario> todos = usuarioRepository.findAll();

        assertEquals(2, todos.size());
    }

    @Test
    void TestEliminarUsuario() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Eliminar", "eliminar@mail.com"));
        usuarioRepository.deleteById(usuario.getId());

        Optional<Usuario> resultado = usuarioRepository.findById(usuario.getId());
        assertTrue(resultado.isEmpty());
    }

}
