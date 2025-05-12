package org.example.TestControladores;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Controladores.PrestamoController;
import org.example.Enum.EstadoLibro;
import org.example.Servicios.PrestamoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrestamoControllerTest {

    @Mock
    private PrestamoService prestamoService;

    @InjectMocks
    private PrestamoController prestamoController;

    @Test
    void TestObtenerTodosPrestamos() {
        Long id1 = 1L;
        Long id2 = 2L;
        Libro libro1 = new Libro(1L, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(2L, "123-456-780", "Once Minutos", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Prestamo prestamo1 = new Prestamo(id1, libro1, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        Prestamo prestamo2 = new Prestamo(id2, libro2, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        List<Prestamo> listaPrestamos = List.of(prestamo1, prestamo2);

        when(prestamoService.obtenerTodosPrestamos()).thenReturn(listaPrestamos);

        List<Prestamo> resultado = prestamoController.obtenerTodosPrestamos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(prestamoService).obtenerTodosPrestamos();
    }

    @Test
    void busquedaIDExiste() {
        Long id = 1L;
        Long id1 = 1L;
        Long id2 = 2L;
        Libro libro1 = new Libro(1L, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(2L, "123-456-780", "Once Minutos", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Prestamo prestamoEsperado = new Prestamo(id1, libro1, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        Prestamo prestamo2 = new Prestamo(id2, libro2, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));

        when(prestamoService.buscarPorId(id)).thenReturn(prestamoEsperado);

        Prestamo resultado = prestamoController.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(prestamoService).buscarPorId(id);
    }

    @Test
    void TestCrearPrestamo() {
        Long id1 = 1L;
        Libro libro1 = new Libro(1L, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Prestamo prestamo = new Prestamo(id1, libro1, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));

        when(prestamoService.guardarPrestamo(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoController.guardarPrestamo(prestamo);

        assertNotNull(resultado);
        assertEquals(prestamo, resultado);
        verify(prestamoService).guardarPrestamo(prestamo);
    }

    @Test
    void TestActualizarPrestamo() {
        Long id = 1L;
        Long id1 = 1L;
        Libro libro1 = new Libro(1L, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(2L, "123-456-780", "Once Minutos", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Usuario Usuario1 = new Usuario(1L, "Daniel de Rossi", "daniel@gmail.com");
        Prestamo prestamo = new Prestamo(id1, libro1, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));
        Prestamo prestamoActualizado = new Prestamo(id1, libro2, Usuario1,LocalDate.parse("2025-04-04"), LocalDate.parse("2025-05-04"));


        when(prestamoService.actualizarPrestamo(id, prestamo)).thenReturn(prestamoActualizado);

        Prestamo resultado = prestamoController.actualizarPrestamo(id, prestamo);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(prestamoService).actualizarPrestamo(id, prestamo);
    }

}
