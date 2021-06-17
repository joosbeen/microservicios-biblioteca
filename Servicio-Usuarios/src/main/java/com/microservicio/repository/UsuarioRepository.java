package com.microservicio.repository;

import com.microservicio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author benito
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public abstract Usuario findByIdentificacion(String identificacion);
    
	Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	Usuario obtenerPorUsername(String username);
}
