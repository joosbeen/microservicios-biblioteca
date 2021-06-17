package com.servicio.oauth2.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.servicio.oauth2.server.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeingClient {

	@GetMapping("/search/buscar-username")
	Usuario findByUsername(@RequestParam(name = "username") String username);
	
	@PutMapping("/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);

}
