package com.example.springboot.service;

import com.example.springboot.entity.LibroEntity;
import java.util.List;

/**
 *
 * @author benito
 */
public interface LibroService {

    List<LibroEntity> findAll();

    LibroEntity findById(Long id);

    LibroEntity create(LibroEntity libroEntity);

    LibroEntity update(LibroEntity libroEntity);

    List<LibroEntity> findByNombre(String nombre);

    List<LibroEntity> findByEditorial(String editorial);

    List<LibroEntity> findByAutor(String autor);

    List<LibroEntity> findByGenero(String genero);

    List<LibroEntity> findByPaisAutor(String paisAutor);

}
