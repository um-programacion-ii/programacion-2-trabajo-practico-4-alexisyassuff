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

    @GetMapping
    public List<Prestamo> obtenerTodosPrestamos() {
        return prestamoService.obtenerTodosPrestamos();
    }

    @GetMapping("/{id}")
    public Prestamo buscarPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    @PostMapping
    public Prestamo guardarPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.guardarPrestamo(prestamo);
    }

    @PutMapping("/{id}")
    public Prestamo actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizarPrestamo(id, prestamo);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
    }
}
