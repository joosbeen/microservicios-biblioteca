package com.microservicio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.microservicio.entity.Rol;
import com.microservicio.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// TODO Auto-generated method stub
		config.exposeIdsFor(Usuario.class, Rol.class);
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
	}

	/**
	 * Confiracion para mostrar los id de los registros
	 */
	/*@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}*/

}
