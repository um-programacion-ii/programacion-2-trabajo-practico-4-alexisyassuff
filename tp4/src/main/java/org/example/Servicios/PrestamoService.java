package org.example.Servicios;

import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;

import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    Prestamo buscarPorId(Long id);
    List<Prestamo> obtenerTodosPrestamos();
    Prestamo guardarPrestamo(Prestamo prestamo);
    void eliminarPrestamo(Long id);
    Prestamo actualizarPrestamo(Long id,  Prestamo prestamo);
}
