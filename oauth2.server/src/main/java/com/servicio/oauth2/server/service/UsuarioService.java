package com.servicio.oauth2.server.service;

import com.servicio.oauth2.server.entity.Usuario;

public interface UsuarioService {

	Usuario findByUsername(String username);

	Usuario update(Usuario usuario, Long id);

}
