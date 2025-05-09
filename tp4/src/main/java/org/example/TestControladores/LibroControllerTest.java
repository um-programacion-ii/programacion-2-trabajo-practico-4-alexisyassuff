package org.example.TestControladores;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Clases.Libro;
import org.example.Controladores.LibroController;
import org.example.Enum.EstadoLibro;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.Servicios.LibroService;
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
public class LibroControllerTest {

    @Mock
    private LibroService libroService;

    @InjectMocks
    private LibroController libroController;

    @Test
    void TestObtenerTodosLibros() {
        Libro libro1 = new Libro(1L, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(2L, "123-456-780", "Once Minutos", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        List<Libro> listaLibros = List.of(libro1, libro2);

        when(libroService.obtenerTodosLibros()).thenReturn(listaLibros);

        List<Libro> resultado = libroController.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(libroService).obtenerTodosLibros();
    }

    @Test
    void busquedaIsbnExiste() {
        String isbn = "123-456-789";
        Libro libroEsperado = new Libro(1L, isbn, "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);

        when(libroService.buscarPorIsbn(isbn)).thenReturn(libroEsperado);

        Libro resultado = libroController.buscarPorIsbn(isbn);

        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroService).buscarPorIsbn(isbn);
    }

    @Test
    void TestExistePorId() {
        Long id = 1L;
        Libro libroEsperado = new Libro(id, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);

        when(libroService.buscarPorId(id)).thenReturn(libroEsperado);

        Libro resultado = libroController.obtenerPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(libroService).buscarPorId(id);
    }

    @Test
    void TestCrearLibro() {
        Libro libro = new Libro(1L, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);

        when(libroService.guardarLibro(libro)).thenReturn(libro);

        Libro resultado = libroController.crear(libro);

        assertNotNull(resultado);
        assertEquals(libro, resultado);
        verify(libroService).guardarLibro(libro);
    }

    @Test
    void TestActualizarLibro() {
        Long id = 1L;
        Libro libro = new Libro(id, "123-456-789", "El Alquimista", "Paulo Coelho", EstadoLibro.DISPONIBLE);
        Libro libroActualizado = new Libro(id, "123-456-789", "Once Minutos", "Paulo Coelho Hijo", EstadoLibro.PRESTADO);

        when(libroService.actualizarLibro(id, libro)).thenReturn(libroActualizado);

        Libro resultado = libroController.actualizar(id, libro);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(libroService).actualizarLibro(id, libro);
    }


}
