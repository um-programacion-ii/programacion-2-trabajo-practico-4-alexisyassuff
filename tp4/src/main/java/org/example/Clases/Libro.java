package org.example.Clases;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Enum.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadoLibro estado;
}

