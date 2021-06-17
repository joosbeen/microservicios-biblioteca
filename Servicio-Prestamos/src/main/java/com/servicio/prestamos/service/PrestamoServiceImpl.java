/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.prestamos.service;

import com.servicio.prestamos.entity.Libro;
import com.servicio.prestamos.entity.Prestamo;
import com.servicio.prestamos.entity.Usuario;
import com.servicio.prestamos.repository.PrestamoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author benito
 */
@Service
public class PrestamoServiceImpl implements PrestamoService {
    
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public Prestamo create(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo findById(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Prestamo> findByLibro(Libro libro) {
        return prestamoRepository.findByLibro(libro);
    }

    @Override
    public List<Prestamo> findByUsuario(Usuario usuario) {
        return prestamoRepository.findByUsuario(usuario);
    }

    @Override
    public Prestamo update(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }
    
}
