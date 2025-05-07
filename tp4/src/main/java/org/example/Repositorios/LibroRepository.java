package org.example.Repositorios;
import org.example.Clases.*;

import java.util.List;
import java.util.Optional;

//Contrato lo que debe hacer el repositorio ("BD")
public interface LibroRepository {
        Libro save(Libro libro);
        //Optional devuelve 0 o 1 solo, no hay busqueda parcial
        Optional<Libro> findById(Long id);
        Optional<Libro> findByIsbn(String isbn);
        //List pq pueden ser cero, uno o muchos
        List<Libro> findAll();
        void deleteById(Long id);
        boolean existsById(Long id);
}
