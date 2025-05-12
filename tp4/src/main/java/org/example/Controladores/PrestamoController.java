package org.example.Controladores;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Servicios.LibroService;
import org.example.Servicios.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }
    // Obtener todos los préstamos de la biblioteca
    @GetMapping
    public List<Prestamo> obtenerTodosPrestamos() {
        return prestamoService.obtenerTodosPrestamos();
    }

    // Buscar un préstamo por su ID
    @GetMapping("/{id}")
    public Prestamo buscarPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    // Crear un nuevo registro de préstamo
    @PostMapping
    public Prestamo guardarPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.guardarPrestamo(prestamo);
    }

    // Actualizar los detalles de un préstamo existente
    @PutMapping("/{id}")
    public Prestamo actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizarPrestamo(id, prestamo);
    }
    // Eliminar un registro de préstamo
    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
    }
}
