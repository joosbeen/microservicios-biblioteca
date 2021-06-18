package com.servicio.oauth2.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.servicio.oauth2.server.client.UsuarioFeingClient;
import com.servicio.oauth2.server.entity.Usuario;

import feign.FeignException;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioFeingClient usuarioFeingClient;

	/*
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
	*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			
			Usuario _usuario = usuarioFeingClient.findByUsername(username);

			if (_usuario == null) {
				logger.error("Error en el login, no existe el usuario " + username + ".");
				throw new UsernameNotFoundException("Error en el login, no existe el usuario " + username + ".");
			}

			List<GrantedAuthority> authorities = _usuario.getRoles().stream()
					.map(r -> new SimpleGrantedAuthority(r.getRol()))
					.peek(aut -> logger.info("Role: " + aut.getAuthority())).collect(Collectors.toList());
			logger.info("USername: " + username);
			return new User(_usuario.getUsername(), _usuario.getPassword(), _usuario.isEnabled(), true, true, true,
					authorities);
		} catch (FeignException e) {
			
			String msg = "Error en el login, no existe el usuario " + username + ".";
			
			logger.error(msg);
			
			throw new UsernameNotFoundException(msg);
		}
	}

	@Override
	public Usuario findByUsername(String username) {

		try {
			return usuarioFeingClient.findByUsername(username);
		} catch (Exception e) {
			logger.error(String.format("Error al buscar el usario: %s", username));
			return null;
		}
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		// TODO Auto-generated method stub
		return usuarioFeingClient.update(usuario, id);
	}

}
