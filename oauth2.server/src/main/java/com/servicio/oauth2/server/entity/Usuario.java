package com.servicio.oauth2.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author benito
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = { "identificacion" }))
public class Usuario implements Serializable {
	/**
	 * 
	 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El campo nombre es obligatorio")
	private String nombre;

	@NotEmpty(message = "El campo apellidos es obligatorio")
	private String apellidos;

	@NotEmpty(message = "El campo identificacion es obligatorio")
	private String identificacion;

	@NotEmpty(message = "El campo domicilio es obligatorio")
	private String domicilio;

	@NotEmpty(message = "El campo poblacion es obligatorio")
	private String poblacion;

	@NotEmpty(message = "El campo provincia es obligatorio")
	private String provincia;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	@Column(unique = true, length = 100)
	private String email;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
	private List<Rol> roles;

	private Integer intentos;

	public Usuario() {
	}

	public Usuario(Long id, String nombre, String apellidos, String identificacion, String domicilio, String poblacion,
			String provincia, Date fechaNacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.identificacion = identificacion;
		this.domicilio = domicilio;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.fechaNacimiento = fechaNacimiento;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	@Override
	public int hashCode() {
		int hash = 5;
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
		final Usuario other = (Usuario) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", identificacion="
				+ identificacion + ", domicilio=" + domicilio + ", poblacion=" + poblacion + ", provincia=" + provincia
				+ ", fechaNacimiento=" + fechaNacimiento + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", enabled=" + enabled + ", roles=" + roles + ", intentos=" + intentos + "]";
	}

}
