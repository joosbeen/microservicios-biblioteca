package com.servicio.prestamos.repository;

import com.servicio.prestamos.entity.Libro;
import com.servicio.prestamos.entity.Prestamo;
import com.servicio.prestamos.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author benito
 */
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    
    public abstract List<Prestamo> findByLibro(Libro libro);
    
    public abstract List<Prestamo> findByUsuario(Usuario usuario);
    
}
