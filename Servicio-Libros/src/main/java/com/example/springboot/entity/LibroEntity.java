package com.example.springboot.entity;

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
@Entity
@Table(name = "libros")
public class LibroEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El campo editorial es obligatorio.")
    private String editorial;

    @NotBlank(message = "El campo autor es obligatorio.")
    private String autor;

    @NotBlank(message = "El campo genero es obligatorio.")
    private String genero;

    @NotBlank(message = "El campo pais es obligatorio.")
    private String paisAutor;

    @NotNull(message = "El numero de paginas no es valido.")
    private Integer numPaginas;

    @NotNull(message = "El numero de paginas no es valido.")
    private Integer fechaEdicion;

    private Double precio;

    public LibroEntity() {
    }

    public LibroEntity(Long id, String nombre, String editorial, String autor, String genero, String paisAutor, Integer numPaginas, Integer fechaEdicion, Double precio) {
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
        final LibroEntity other = (LibroEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LibroEntity{" + "id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", autor=" + autor + ", genero=" + genero + ", paisAutor=" + paisAutor + ", numPaginas=" + numPaginas + ", fechaEdicion=" + fechaEdicion + ", precio=" + precio + '}';
    }

}
