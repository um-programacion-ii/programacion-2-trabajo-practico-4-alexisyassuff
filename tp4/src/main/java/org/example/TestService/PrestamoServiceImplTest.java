package org.example.TestService;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Enum.EstadoLibro;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.ImplementacionesServicios.LibroServiceImpl;
import org.example.ImplementacionesServicios.PrestamoServiceImpl;
import org.example.Repositorios.LibroRepository;
import org.example.Repositorios.PrestamoRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrestamoServiceImplTest {
    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void testeoBusquedaId() {
        Long id1 = 1L;
        Libro libro1 = new Libro(1L, "123-456-789", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Prestamo prestamo1 = new Prestamo(id1, libro1, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        when(prestamoRepository.findById(id1)).thenReturn(Optional.of(prestamo1));

        Prestamo resultado = prestamoService.buscarPorId(id1);

        assertNotNull(resultado);
        assertEquals(prestamo1, resultado);
    }
    @Test
    void testeoEliminarPrestamo() {
        Long id1 = 1L;
        Long id2 = 2L;
        Libro libro1 = new Libro(1L, "123-456-789", "Test Book", "Test Author", EstadoLibro.PRESTADO);
        Libro libro2 = new Libro(1L, "123-456-780", "Test Booker", "Test Author", EstadoLibro.PRESTADO);
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Prestamo prestamo1 = new Prestamo(id1, libro1, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        Prestamo prestamo2 = new Prestamo(id2, libro2, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        List<Prestamo> listaPrestamos  = List.of(prestamo1, prestamo2);
        List<Prestamo> resultadoEsperado = List.of(prestamo1);
        doNothing().when(prestamoRepository).deleteById(id2);
        when(prestamoRepository.findAll()).thenReturn(resultadoEsperado);

        List<Prestamo> resultado = prestamoService.eliminarPrestamo(id2);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertFalse(resultado.contains(prestamo2));
        assertTrue(resultado.contains(prestamo1));
    }

    @Test
    void testGuardarPrestamo() {
        Prestamo prestamo = new Prestamo(
                1L,
                new Libro(1L, "123-456", "Libro Test", "Autor", EstadoLibro.DISPONIBLE),
                new Usuario(1L, "Nombre", "email@test.com"),
                LocalDate.parse("2025-04-01"),
                LocalDate.parse("2025-04-30")
        );

        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.guardarPrestamo(prestamo);

        assertNotNull(resultado);
        assertEquals(prestamo, resultado);
        verify(prestamoRepository).save(prestamo);
    }


    @Test
    void testActualizarPrestamo() {
        Long id = 1L;
        Prestamo prestamo = new Prestamo(
                null,
                new Libro(1L, "123-456", "Libro Test", "Autor", EstadoLibro.DISPONIBLE),
                new Usuario(1L, "Nombre", "email@test.com"),
                LocalDate.parse("2025-04-01"),
                LocalDate.parse("2025-04-30")
        );

        Prestamo prestamoActualizado = new Prestamo(
                id,
                prestamo.getLibro(),
                prestamo.getUsuario(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion()
        );

        when(prestamoRepository.existsById(id)).thenReturn(true);
        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamoActualizado);

        Prestamo resultado = prestamoService.actualizarPrestamo(id, prestamo);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(prestamoRepository).existsById(id);
        verify(prestamoRepository).save(prestamo);
    }

}
