package org.example.ImplementacionesServicios;

import org.example.Clases.Prestamo;
import org.example.Clases.Usuario;
import org.example.Excepciones.LibroNoEncontradoExceptionId;
import org.example.Excepciones.PrestamoNoEncontradoException;
import org.example.Excepciones.UsuarioNoEncontradoException;
import org.example.Repositorios.PrestamoRepository;
import org.example.Repositorios.UsuarioRepository;
import org.example.Servicios.PrestamoService;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));
                }

    @Override
    public List<Prestamo> obtenerTodosPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo actualizarPrestamo(Long id, Prestamo prestamo) {
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNoEncontradoException(id);
        }
        prestamo.setId(id);
        return prestamoRepository.save(prestamo);
    }


}
