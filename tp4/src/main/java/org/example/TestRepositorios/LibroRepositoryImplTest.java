package org.example.TestRepositorios;
import org.example.Clases.Libro;
import org.example.Enum.EstadoLibro;
import org.example.ImplementacionesRepositorios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class LibroRepositoryImplTest {

    private LibroRepositoryImpl libroRepository;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
    }

    @Test
    void busquedaIdExiste() {
        Libro libro = libroRepository.save(new Libro(null, "333-444", "Otro Libro", "Otro Autor", EstadoLibro.PRESTADO));
        Optional<Libro> encontrado = libroRepository.findById(libro.getId());

        assertTrue(encontrado.isPresent());
        assertEquals(libro.getId(), encontrado.get().getId());
    }

    @Test
    void busquedaIdNOExiste() {
        Optional<Libro> resultado = libroRepository.findById(999L);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void busquedaIsbnExiste() {
        Libro libro = libroRepository.save(new Libro(null, "abc-123", "Título", "Autor", EstadoLibro.DISPONIBLE));
        Optional<Libro> resultado = libroRepository.findByIsbn("abc-123");

        assertTrue(resultado.isPresent());
        assertEquals("abc-123", resultado.get().getIsbn());
    }

    @Test
    void busquedaIsbnNOExiste() {
        Optional<Libro> resultado = libroRepository.findByIsbn("no-existe");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void TestObtenerTodosLibros() {
        libroRepository.save(new Libro(null, "1", "Libro1", "Autor1", EstadoLibro.DISPONIBLE));
        libroRepository.save(new Libro(null, "2", "Libro2", "Autor2", EstadoLibro.PRESTADO));

        List<Libro> todos = libroRepository.findAll();

        assertEquals(2, todos.size());
    }

    @Test
    void TestEliminarLibro() {
        Libro libro = libroRepository.save(new Libro(null, "eliminar", "Test", "Autor", EstadoLibro.DISPONIBLE));
        libroRepository.deleteById(libro.getId());

        Optional<Libro> resultado = libroRepository.findById(libro.getId());
        assertTrue(resultado.isEmpty());
    }

    @Test
    void TestExistePorId() {
        Libro libro = libroRepository.save(new Libro(null, "existente", "Existente", "Autor", EstadoLibro.DISPONIBLE));
        boolean existe = libroRepository.existsById(libro.getId());

        assertTrue(existe);
    }

    @Test
    void TestNoExistePorId() {
        boolean existe = libroRepository.existsById(123456L);
        assertFalse(existe);
    }
}
