package com.servicio.prestamos.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author benito
 */
@Entity(name = "libros")
//@Table(name = "libros")
public class Libro implements Serializable {

    @Id
    private Long id;

    private String nombre;

    private String editorial;

    private String autor;

    private String genero;

    private String paisAutor;

    private Integer numPaginas;

    private Integer fechaEdicion;

    private Double precio;

    public Libro() {
    }

    public Libro(Long id, String nombre, String editorial, String autor, String genero, String paisAutor, Integer numPaginas, Integer fechaEdicion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.editorial = editorial;
        this.autor = autor;
        this.genero = genero;
        this.paisAutor = paisAutor;
        this.numPaginas = numPaginas;
        this.fechaEdicion = fechaEdicion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPaisAutor() {
        return paisAutor;
    }

    public void setPaisAutor(String paisAutor) {
        this.paisAutor = paisAutor;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public Integer getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Integer fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", autor=" + autor + ", genero=" + genero + ", paisAutor=" + paisAutor + ", numPaginas=" + numPaginas + ", fechaEdicion=" + fechaEdicion + ", precio=" + precio + '}';
    }

}