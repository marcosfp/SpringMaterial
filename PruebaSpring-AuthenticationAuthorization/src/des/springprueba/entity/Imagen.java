package des.springprueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGEN")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_IMAGEN")
    private long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;

    
    @OneToOne
    @JoinColumn(name = "ID_PROFESOR")
    @MapsId
    private Profesor profesor;
    
    
    public Imagen() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Imagen(String name, byte[] image) {
        super();
        this.nombre = name;
        this.imagen = image;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}