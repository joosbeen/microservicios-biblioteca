package com.servicio.oauth2.server.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.servicio.oauth2.server.entity.Usuario;
import com.servicio.oauth2.server.service.UsuarioService;

import brave.Tracer;

/**
 * Los estados cuando un usuario se esta, llamarlo en la clase de configuracion de spring securiy.
 * @author benito
 *
 */
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@Autowired
	private Tracer tracer;


	/**
	 * Algun proceso que se debe realizar cuando el login sea exitoso.
	 */
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		
		log.info("=====> Cargando autenticacion....");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		log.info("=====> userDetails:" + userDetails.toString());

		Usuario _usuario = usuarioService.findByUsername(authentication.getName());
		
		log.info("=====> userDetails:" + _usuario.toString());
		
		
		if (_usuario != null && _usuario.getIntentos()>0) {
			_usuario.setIntentos(0);
			usuarioService.update(_usuario, _usuario.getId());
		}
		
		String _msg = "=====> Success Login:" + userDetails.getUsername();
		
		log.info(_msg);
		System.out.println(_msg);
		
		
	}

	/**
	 * En caso de que la autenticacion no sea exitosa.
	 */
	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String _msg = "Error en el login:" + exception.getMessage();
		log.error(_msg);
		tracer.currentSpan().tag("error.message", _msg);
		
		
		try {
			
			StringBuilder errors = new StringBuilder();
			errors.append(_msg);
			
			Usuario _usuario = usuarioService.findByUsername(authentication.getName());
			
			if (_usuario.getIntentos() == null) {
				_usuario.setIntentos(0);
			} 
			
			log.info("Intentos actual es de:" + _usuario.getIntentos());
			_usuario.setIntentos(_usuario.getIntentos()+1);
			log.info("Intentos actualizado es de:" + _usuario.getIntentos());
			errors.append(" - Intentos actualizado es de:" + _usuario.getIntentos());
			
			if (_usuario.getIntentos()>=3) {
				log.error(String.format("El usuario %s des-habilitado por máximo de de intentos", _usuario.getUsername()));
				_usuario.setEnabled(false);
				errors.append(String.format(" - El usuario %s des-habilitado por máximo de de intentos", _usuario.getUsername()));
			}
			
			usuarioService.update(_usuario, _usuario.getId());
			tracer.currentSpan().tag("error.message", errors.toString());
			
		} catch (Exception e) {
			
			String msg = String.format("El usuario %s no existe en el sistema", authentication.getName());
			log.error(msg);
			tracer.currentSpan().tag("error.message", msg);
			
		}
	}

}
