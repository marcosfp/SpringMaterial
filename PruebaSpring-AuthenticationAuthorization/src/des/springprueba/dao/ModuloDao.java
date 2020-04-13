package des.springprueba.dao;

import java.util.List;

import des.springprueba.entity.Modulo;
import des.springprueba.entity.Profesor;

public interface ModuloDao extends GenericDao<Modulo>{

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);
	
	public Modulo agregarProfesor(long idModulo, Profesor profesor);
	
	public Modulo eliminarProfesor(long idModulo, Profesor profesor);

}
