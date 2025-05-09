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

    @Mock
    private Libro libroMock;

    @Mock
    private Usuario usuarioMock;

    private PrestamoRepositoryImpl prestamoRepository;

    @BeforeEach
    void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();
    }

    @Test
    void buscarPorId() {
        Prestamo prestamo = new Prestamo(1L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));
        prestamoRepository.save(prestamo);

        Optional<Prestamo> resultado = prestamoRepository.findById(prestamo.getId());

        assertTrue(resultado.isPresent());
        assertEquals(prestamo.getId(), resultado.get().getId());
    }

    @Test
    void buscarPorUsuario() {
        Prestamo prestamo = new Prestamo(1L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));
        prestamoRepository.save(prestamo);

        Optional<Prestamo> resultado = prestamoRepository.findByUser(usuarioMock);

        assertTrue(resultado.isPresent());
        assertEquals(usuarioMock, resultado.get().getUsuario());
    }

    @Test
    void buscarPorLibro() {
        Prestamo prestamo = new Prestamo(1L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));
        prestamoRepository.save(prestamo);

        Optional<Prestamo> resultado = prestamoRepository.findByBook(libroMock);

        assertTrue(resultado.isPresent());
        assertEquals(libroMock, resultado.get().getLibro());
    }

    @Test
    void listarTodos() {
        Prestamo prestamo1 = new Prestamo(1L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));
        Prestamo prestamo2 = new Prestamo(2L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));

        prestamoRepository.save(prestamo1);
        prestamoRepository.save(prestamo2);

        List<Prestamo> prestamos = prestamoRepository.findAll();

        assertEquals(2, prestamos.size());
    }

    @Test
    void eliminarPorId() {
        Prestamo prestamo = new Prestamo(1L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));
        prestamoRepository.save(prestamo);

        prestamoRepository.deleteById(prestamo.getId());

        assertFalse(prestamoRepository.findById(prestamo.getId()).isPresent());
    }

    @Test
    void existePorId() {
        Prestamo prestamo = new Prestamo(1L, libroMock, usuarioMock, LocalDate.parse("2025-04-30"), LocalDate.parse("2025-05-30"));
        prestamoRepository.save(prestamo);

        boolean existe = prestamoRepository.existsById(prestamo.getId());

        assertTrue(existe);
    }

}
