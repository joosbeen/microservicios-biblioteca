
package com.microservicio.services;

import com.microservicio.entity.Usuario;
import java.util.List;

/**
 *
 * @author benito
 */
public interface UsuarioService {
    
    Usuario create(Usuario usuario);
    
    List<Usuario> findAll();
    
    Usuario findById(Long id);
    
    Usuario findByIdentificacion(String identificacion);
    
    Usuario update(Usuario usuario);
    
}
