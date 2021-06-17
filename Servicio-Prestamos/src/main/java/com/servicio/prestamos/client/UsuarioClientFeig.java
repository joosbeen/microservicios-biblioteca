package com.servicio.prestamos.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.prestamos.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioClientFeig {

	@PostMapping(value = "/")
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario, BindingResult br);

	@GetMapping(value = "/")
	public ResponseEntity<List<Usuario>> findAll();

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable("id") Long id);

	@GetMapping(value = "/identificacion/{identificacion}")
	public ResponseEntity<Usuario> findByIdentificacion(@PathVariable("identificacion") String identificacion);

	@PutMapping(value = "/")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, BindingResult br);

}
