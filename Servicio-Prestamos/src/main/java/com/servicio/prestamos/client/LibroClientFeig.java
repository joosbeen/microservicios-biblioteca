package com.servicio.prestamos.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.prestamos.entity.Libro;

@FeignClient(name = "servicio-libros")
public interface LibroClientFeig {

	@GetMapping(value = "/")
	public ResponseEntity<?> findAll();

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id", required = true) Long id);

	@PostMapping(value = "/")
	public ResponseEntity<Libro> create(@Valid @RequestBody Libro le, BindingResult br);

	@PutMapping(value = "/")
	public ResponseEntity<Libro> update(@Valid @RequestBody Libro le, BindingResult br);

	@GetMapping(value = "/buscar/{campo}/{value}")
	public ResponseEntity<List<Libro>> buscar(@PathVariable(name = "campo") String campo,
			@PathVariable(name = "value") String value);

}
