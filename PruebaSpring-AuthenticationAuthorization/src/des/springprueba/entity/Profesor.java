package des.springprueba.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PROFESOR")
public class Profesor implements Serializable {


	private static final long serialVersionUID = -8668594760203621162L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_PROFESOR")
	private Long idProfesor;
	
	@NotNull @Size(min=6, max=15)
	@Column(name = "NICKNAME")
	private String username;
	
	@NotNull @Size(min=8, max=80)
	@Column(name = "PASSWORD")
	private String password;

	@NotNull @Size(min=4, max=15)
	@Column(name = "NOMBRE")
	private String nombreProfesor;

	@NotNull @Size(min=4, max=15)
	@Column(name = "APELLIDOS")
	private String apellidosProfesor;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Email> emails = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PROFESOR_MODULO", 
	joinColumns = @JoinColumn(name = "ID_PROFESOR"),
	inverseJoinColumns = @JoinColumn(name = "ID_MODULO"))
	private Set<Modulo> modulos = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidosProfesor == null) ? 0 : apellidosProfesor.hashCode());
		result = prime * result + ((nombreProfesor == null) ? 0 : nombreProfesor.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (apellidosProfesor == null) {
			if (other.apellidosProfesor != null)
				return false;
		} else if (!apellidosProfesor.equals(other.apellidosProfesor))
			return false;
		if (nombreProfesor == null) {
			if (other.nombreProfesor != null)
				return false;
		} else if (!nombreProfesor.equals(other.nombreProfesor))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	
}
