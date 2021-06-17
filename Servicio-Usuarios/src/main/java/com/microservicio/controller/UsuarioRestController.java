package com.microservicio.controller;

import com.microservicio.entity.Usuario;
import com.microservicio.services.UsuarioService;
import com.microservicio.util.FormValid;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author benito
 */
@RestController
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario, BindingResult br) {

        if (br.hasErrors()) {
            logger.error("Error el campos");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, FormValid.errorMessages(br));
        }

        Usuario _usuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

        if (_usuario != null) {
            logger.error("Error, la identificacion ya existe.");
            logger.error(_usuario.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario con la identificación ya se encuentra registrado.");
        }

        _usuario = usuarioService.create(usuario);

        if (_usuario == null) {
            logger.error("Error, no se pudo registrar el usuario.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FormValid.errorMessages(br));
        }

        logger.info("Registro exitosa.");
        return ResponseEntity.ok(_usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {

        List<Usuario> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty() || usuarios.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro ningun registro.");
        }

        logger.info("Busqueda exitosa.");
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {

        Usuario usuarios = usuarioService.findById(id);

        if (usuarios == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro ningun registro.");
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/identificacion/{identificacion}")
    public ResponseEntity<Usuario> findByIdentificacion(@PathVariable("identificacion") String identificacion) {

        Usuario usuarios = usuarioService.findByIdentificacion(identificacion);

        if (usuarios == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro ningun registro.");
        }

        logger.info("Busqueda exitosa.");
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, BindingResult br) {

        if (br.hasErrors()) {
            logger.error("Error el campos");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, FormValid.errorMessages(br));
        }

        // validar si existe el usuario
        Usuario _usuario = usuarioService.findById(usuario.getId());

        if (_usuario == null) {
            logger.error("Error, no se encontro el usuario.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe.");
        }

        // comprobar el atributo identificacion
        if (!usuario.getIdentificacion().equals(_usuario.getIdentificacion())) {

            _usuario = usuarioService.findByIdentificacion(usuario.getIdentificacion());

            if (_usuario != null) {
                logger.error("Error, la identificacion ya existe.");
                logger.error(_usuario.toString());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario con la identificación ya se encuentra registrado.");
            }
        }

        //crear el usuario
        //usuario.setFechaNacimiento(_usuario.getFechaNacimiento());
        _usuario = usuarioService.create(usuario);

        if (_usuario == null) {
            logger.error("Error, no se pudo registrar el usuario.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FormValid.errorMessages(br));
        }

        logger.info("El usuario se actulizao");
        return ResponseEntity.ok(_usuario);
    }

    @GetMapping(value = "/search/buscar-username")
    public ResponseEntity<Usuario> findByUsername(@RequestParam(name = "username") String username) {

        Usuario usuarios = usuarioService.findByUsername(username);

        if (usuarios == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro ningun registro.");
        }

        logger.info("Busqueda exitosa.");
        return ResponseEntity.ok(usuarios);
    }
    
}
