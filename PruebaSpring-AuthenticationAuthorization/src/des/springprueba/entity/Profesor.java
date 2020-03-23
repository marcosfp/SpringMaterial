package des.springprueba.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROFESOR")
public class Profesor implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_PROFESOR")
	private Long idProfesor;
	
	@Column(name = "NICKNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NOMBRE")
	private String nombreProfesor;

	@Column(name = "APELLIDOS")
	private String apellidosProfesor;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Email> emails = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PROFESOR_MODULO", 
	joinColumns = @JoinColumn(name = "ID_PROFESOR"),
	inverseJoinColumns = @JoinColumn(name = "ID_MODULO"))
	private Set<Modulo> modulos = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PROFESOR_ROL", 
	joinColumns = @JoinColumn(name = "ID_PROFESOR"),
	inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> roles = new HashSet<>();
	
	
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public boolean addEmails(Email email) {
		email.setProfesor(this);
		return getEmails().add(email);
	}

	public void removeEmails(Email email) {
		getEmails().remove(email);
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public String getApellidosProfesor() {
		return apellidosProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public void setApellidosProfesor(String apellidosProfesor) {
		this.apellidosProfesor = apellidosProfesor;
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

	@Override
	public String toString() {
		return "Profesor [idProfesor=" + idProfesor + ", username=" + username + ", password=" + password
				+ ", nombreProfesor=" + nombreProfesor + ", apellidosProfesor=" + apellidosProfesor + ", emails="
				+ emails + ", modulos=" + modulos + ", roles=" + roles + "]";
	}

	
	
}
