package org.example.TestRepositorios;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Enum.EstadoLibro;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.ImplementacionesRepositorios.PrestamoRepositoryImpl;
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
public class PrestamoRepositoryImplTest {
    private PrestamoRepositoryImpl prestamoRepository;

    private Usuario usuario;
    private Libro libro;

    @BeforeEach
    void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();
        usuario = new Usuario(1L, "Juan", "juan@mail.com"); // sin "Usuario" delante
        libro = new Libro(1L, "111-222-333", "Libro Prueba", "Autor", EstadoLibro.DISPONIBLE);
        Libro libro1 = new Libro(2L, "111-222-555", "Libro Prueba 1", "Autor", EstadoLibro.PRESTADO);
    }



    @Test
    void buscarPorId() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo(1L, libro, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));
        Optional<Prestamo> resultado = prestamoRepository.findById(prestamo.getId());

        assertTrue(resultado.isPresent());
        assertEquals(prestamo.getId(), resultado.get().getId());
    }

    @Test
    void buscarPorUsuario() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo(1L, libro, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));
        Optional<Prestamo> resultado = prestamoRepository.findByUser(usuario);

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get().getUsuario());
    }

    @Test
    void buscarPorLibro() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo(1L, libro, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));
        Optional<Prestamo> resultado = prestamoRepository.findByBook(libro);
        assertTrue(resultado.isPresent());
        assertEquals(libro, resultado.get().getLibro());
    }

    @Test
    void listarTodos() {
        Libro libro1 = new Libro(2L, "111-222-555", "Libro Prueba 1", "Autor", EstadoLibro.PRESTADO);
        Prestamo prestamo = prestamoRepository.save(new Prestamo(1L, libro, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));
        Prestamo prestamo1 = prestamoRepository.save(new Prestamo(2L, libro1, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));

        List<Prestamo> prestamos = prestamoRepository.findAll();
        assertEquals(2, prestamos.size());
    }

    @Test
    void eliminarPorId() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo(1L, libro, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));
        prestamoRepository.deleteById(prestamo.getId());

        assertFalse(prestamoRepository.findById(prestamo.getId()).isPresent());
    }

    @Test
    void existePorId() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo(1L, libro, usuario, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30")));
        boolean existe = prestamoRepository.existsById(prestamo.getId());

        assertTrue(existe);
    }
}
