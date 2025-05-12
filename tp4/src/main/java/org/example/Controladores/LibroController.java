package org.example.Controladores;
import org.example.Clases.Libro;
import org.example.Repositorios.*;
import org.example.Servicios.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
    // Obtener todos los libros de la biblioteca

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodosLibros();
    }
    // Buscar un libro por su ISBN

    @GetMapping("/isbn/{isbn}")
    public Libro buscarPorIsbn(@PathVariable String id) {
        return libroService.buscarPorIsbn(id);
    }
    // Obtener un libro por su ID

    @GetMapping("/id/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }
    // Añadir un nuevo libro a la biblioteca
    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }
    // Actualizar los detalles de un libro existente

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizarLibro(id, libro);
    }
    // Eliminar un libro de la biblioteca
    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}
