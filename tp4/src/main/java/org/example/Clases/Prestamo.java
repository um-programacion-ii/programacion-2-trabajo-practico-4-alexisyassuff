package org.example.Clases;

import org.example.Enum.EstadoLibro;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
}
