package com.servicio.prestamos.service;

import com.servicio.prestamos.entity.Libro;
import com.servicio.prestamos.entity.Prestamo;
import com.servicio.prestamos.entity.Usuario;
import java.util.List;

/**
 *
 * @author benito
 */
public interface PrestamoService {
    
    Prestamo create(Prestamo prestamo);
    
    List<Prestamo> findAll();
    
    Prestamo findById(Long id);
    
    List<Prestamo> findByLibro(Libro libro);
    
    List<Prestamo> findByUsuario(Usuario usuario);
    
    Prestamo update(Prestamo prestamo);
    
}
