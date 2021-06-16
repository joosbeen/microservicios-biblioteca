package com.example.springboot.service;

import com.example.springboot.entity.LibroEntity;
import com.example.springboot.repository.LibroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author benito
 */
@Service
public class LibroServiceImpl implements LibroService {
    
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<LibroEntity> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public LibroEntity findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public LibroEntity create(LibroEntity libroEntity) {
        return libroRepository.save(libroEntity);
    }

    @Override
    public LibroEntity update(LibroEntity libroEntity) {
        return libroRepository.save(libroEntity);
    }

    @Override
    public List<LibroEntity> findByNombre(String nombre) {
        return libroRepository.findByNombre(nombre);
    }

    @Override
    public List<LibroEntity> findByEditorial(String editorial) {
        return libroRepository.findByEditorial(editorial);
    }

    @Override
    public List<LibroEntity> findByAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    @Override
    public List<LibroEntity> findByGenero(String genero) {
        return libroRepository.findByGenero(genero);
    }

    @Override
    public List<LibroEntity> findByPaisAutor(String paisAutor) {
        return libroRepository.findByPaisAutor(paisAutor);
    }
    
}
