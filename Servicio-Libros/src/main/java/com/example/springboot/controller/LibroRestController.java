package com.example.springboot.controller;

import com.example.springboot.entity.LibroEntity;
import com.example.springboot.service.LibroService;
import com.example.springboot.util.FormValid;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author benito
 */
@RestController
public class LibroRestController {

    private static final Logger logger = LoggerFactory.getLogger(LibroRestController.class);

    @Autowired
    private LibroService libroService;

    @Autowired
    private FormValid formValid;

    @GetMapping(value = "/")
    public ResponseEntity<?> findAll() {

        logger.info("Lista de libros");

        List<LibroEntity> _libros = libroService.findAll();

        if (_libros.isEmpty() || _libros.size() <= 0) {
            logger.warn("No se encontro contenido.");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro contenido.");
        }

        return ResponseEntity.ok(_libros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id", required = true) Long id) {

        logger.info(String.format("Buscar libro con id=%s", id.toString()));

        LibroEntity _libro = libroService.findById(id);

        if (_libro == null) {
            logger.warn("No se encontro contenido.");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro el libro.");
        }

        return ResponseEntity.ok(_libro);
    }

    @PostMapping
    public ResponseEntity<LibroEntity> create(@Valid @RequestBody LibroEntity le, BindingResult br) {

        logger.info("Procesando registro de libro.");

        if (br.hasErrors()) {
            logger.warn("El formulario es invalido.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formValid.errorMessages(br));
        }

        LibroEntity _le = libroService.create(le);

        if (_le == null) {
            logger.warn("Error al registra el libro.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registra el libro.");
        }

        return ResponseEntity.ok(_le);

    }

    @PutMapping
    public ResponseEntity<LibroEntity> update(@Valid @RequestBody LibroEntity le, BindingResult br) {

        logger.info("Procesando registro de libro.");

        if (br.hasErrors()) {
            logger.warn("El formulario es invalido.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formValid.errorMessages(br));
        }

        LibroEntity _le = libroService.update(le);

        if (_le == null) {
            logger.warn("Error al registra el libro.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registra el libro.");
        }

        return ResponseEntity.ok(_le);

    }

    @GetMapping(value = "/buscar/{campo}/{value}")
    public ResponseEntity<List<LibroEntity>> buscar(@PathVariable(name = "campo") String campo, @PathVariable(name = "value") String value) {

        logger.info(String.format("Procesando busqueda, campo:%s, value:%s.", campo, value));

        if (campo.isBlank() || campo.isEmpty()) {
            logger.warn("Error en el campo value");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"message\":\"El valor es requerido\"}");
        }

        List<LibroEntity> libroEntitys = new ArrayList<>();

        if (campo.equalsIgnoreCase("nombre")) {
            logger.info("Buscando nombre");
            libroEntitys = libroService.findByNombre(value);
        } else if (campo.equalsIgnoreCase("editorial")) {
            logger.info("Buscando editorial");
            libroEntitys = libroService.findByEditorial(value);
        } else if (campo.equalsIgnoreCase("autor")) {
            logger.info("Buscando autor");
            libroEntitys = libroService.findByAutor(value);
        } else if (campo.equalsIgnoreCase("genero")) {
            logger.info("Buscando genero");
            libroEntitys = libroService.findByGenero(value);
        } else if (campo.equalsIgnoreCase("pais")) {
            logger.info("Buscando pais");
            libroEntitys = libroService.findByPaisAutor(value);
        } else {
            logger.warn("Error en la peticion.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"message\":\"Error en la peticion.\"}");
        }

        if (libroEntitys.isEmpty() || libroEntitys.size() <= 0) {
            logger.warn("No se encontro contenido.");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "{\"message\":\"No se encontro contenido.\"}");
        }

        return ResponseEntity.ok(libroEntitys);

    }

}
