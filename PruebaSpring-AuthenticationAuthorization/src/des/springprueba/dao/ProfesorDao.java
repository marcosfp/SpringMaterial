package des.springprueba.dao;

import java.util.List;

import des.springprueba.dto.ProfesorDto;
import des.springprueba.entity.Email;
import des.springprueba.entity.Profesor;

public interface ProfesorDao extends GenericDao<Profesor>{

	public Profesor create (Profesor profesor);
	
	public Profesor buscarPorEmail (String email);
	
	public List<ProfesorDto> buscarPorfesorPorNombreYApellidos(String nombreyapellidos);
	
	public List<Profesor> listarPorfesores();
	
	public Profesor anadirEmail(long idProfesor,Email email);
	
	public Profesor findByUsername(String username);
	
}
