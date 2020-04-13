package des.springprueba.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MODULO")
public class Modulo  implements Serializable {

	private static final long serialVersionUID = 6261463148117483407L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_MODULO")
	private Long idModulo;

	@Column(name = "NOMBRE_MODULO")
	private String nombreModulo;

	@ManyToMany(fetch = FetchType.EAGER,cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "PROFESOR_MODULO", joinColumns = @JoinColumn(name = "ID_MODULO"), inverseJoinColumns = @JoinColumn(name = "ID_PROFESOR"))
	private Set<Profesor> profesores = new HashSet<>();

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public void addProfesor(Profesor profesor) {
		this.profesores.add(profesor);
		profesor.getModulos().add(this);
	}
	
	public void deleteProfesor(Profesor profesor) {
		this.profesores.remove(profesor) ;
		profesor.getEmails().remove(this);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreModulo == null) ? 0 : nombreModulo.hashCode());
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
		Modulo other = (Modulo) obj;
		if (nombreModulo == null) {
			if (other.nombreModulo != null)
				return false;
		} else if (!nombreModulo.equals(other.nombreModulo))
			return false;
		return true;
	}

	
}
