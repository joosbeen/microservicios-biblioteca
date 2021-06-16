package com.example.springboot.repository;

import com.example.springboot.entity.LibroEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author benito
 */
@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    public abstract List<LibroEntity> findByNombre(String nombre);

    public abstract List<LibroEntity> findByEditorial(String editorial);

    public abstract List<LibroEntity> findByAutor(String autor);

    public abstract List<LibroEntity> findByGenero(String genero);

    public abstract List<LibroEntity> findByPaisAutor(String paisAutor);

}
