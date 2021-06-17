package com.servicio.oauth2.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.servicio.oauth2.server.client.UsuarioFeingClient;
import com.servicio.oauth2.server.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioFeingClient usuarioFeingClient;

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
