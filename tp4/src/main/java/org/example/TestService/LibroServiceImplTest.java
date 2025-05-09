package org.example.TestService;

import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Enum.EstadoLibro;
import org.example.Excepciones.LibroNoEncontradoExceptionISBN;
import org.example.ImplementacionesServicios.*;
import org.example.Repositorios.*;
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
public class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        String isbn = "123-456-789";
        Libro libroEsperado = new Libro(1L, isbn, "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libroEsperado));

        Libro resultado = libroService.buscarPorIsbn(isbn);

        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        String isbn = "123-456-789";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoExceptionISBN.class, () ->
                libroService.buscarPorIsbn(isbn)
        );
    }

    @Test
    void testeoEliminarLibro() {
        Long id1 = 1L;
        Long id2 = 2L;
        Libro libro1 = new Libro(1L, "123-456-789", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(1L, "123-456-780", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        List<Libro> listaLibros = List.of(libro1, libro2);
        List<Libro> resultadoEsperado = List.of(libro2);
        doNothing().when(libroRepository).deleteById(id1);
        when(libroRepository.findAll()).thenReturn(resultadoEsperado);

        List<Libro> resultado = libroService.eliminarLibro(id1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertFalse(resultado.contains(libro1));
        assertTrue(resultado.contains(libro2));
    }

    @Test
    void testeoTraerTodosLibrosTamano() {
        Long id1 = 1L;
        Long id2 = 2L;
        Libro libro1 = new Libro(id1, "123-456-789", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(id2, "123-456-780", "Test Booker", "Test Author", EstadoLibro.DISPONIBLE);
        List<Libro> listaLibros = List.of(libro1, libro2);
        when(libroRepository.findAll()).thenReturn(listaLibros);

        List<Libro> resultado = libroService.obtenerTodosLibros();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void testeoTraerTodosLibrosObjetos() {
        Long id1 = 1L;
        Long id2 = 2L;
        Libro libro1 = new Libro(id1, "123-456-789", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(id2, "123-456-780", "Test Booker", "Test Author", EstadoLibro.DISPONIBLE);
        List<Libro> listaLibros = List.of(libro1, libro2);
        when(libroRepository.findAll()).thenReturn(listaLibros);

        List<Libro> resultado = libroService.obtenerTodosLibros();

        assertNotNull(resultado);
        assertTrue(resultado.contains(libro1));
        assertTrue(resultado.contains(libro2));
    }


    @Test
    void testGuardarLibro() {
        Libro libro1 = new Libro(1L, "123-456-789", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);

        when(libroRepository.save(libro1)).thenReturn(libro1);

        Libro resultado = libroService.guardarLibro(libro1);

        assertNotNull(resultado);
        assertEquals(libro1, resultado);
        verify(libroRepository).save(libro1);
    }


    @Test
    void testActualizarLibro() {
        Long id = 1L;
        Libro libro1 = new Libro(id, "123-456-789", "Test Book", "Test Author", EstadoLibro.DISPONIBLE);


        Libro libroActualizado = new Libro(1L, "123-456-789", "Test Book", "Test Author", EstadoLibro.PRESTADO);


        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.save(any(Libro.class))).thenReturn(libroActualizado);

        Libro resultado = libroService.actualizarLibro(id, libro1);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(libroRepository).existsById(id);
        verify(libroRepository).save(libro1);
    }

}
