package com.servicio.prestamos.controller;

import com.servicio.prestamos.entity.Prestamo;
import com.servicio.prestamos.service.PrestamoService;
import com.servicio.prestamos.util.FormValid;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author benito
 */
@RestController
public class PrestamoRestController {

    private final static Logger logger = LoggerFactory.getLogger(PrestamoRestController.class);

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private FormValid formValid;

    @PostMapping
    public ResponseEntity<Prestamo> create(@Valid @RequestBody Prestamo prestamo, BindingResult br) {

        logger.info("Proesando registro de prestamo.");
        logger.info(prestamo.toString());
        String messages = "";

        if (br.hasErrors()) {
            messages = formValid.errorMessages(br);
            logger.error("Formulario invalido, " + messages);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messages);
        }

        Prestamo _prestamo = prestamoService.create(prestamo);

        if (_prestamo == null) {
            formValid.setJsonObjet("message", "No se registro el prestamo del libro");
            messages = formValid.getJsonObject();
            logger.error("Error en el servidor: " + messages);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, messages);
        }

        return ResponseEntity.ok(_prestamo);
    }

}
