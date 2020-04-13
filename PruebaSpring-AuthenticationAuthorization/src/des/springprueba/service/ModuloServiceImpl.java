package des.springprueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.springprueba.dao.ModuloDao;
import des.springprueba.dao.ProfesorDao;
import des.springprueba.entity.Modulo;
import des.springprueba.entity.Profesor;

@Transactional
@Service
public class ModuloServiceImpl implements ModuloService {

	@Autowired
	ModuloDao moduloDao;
	
	@Autowired
	ProfesorDao ProfesorDao;

	@Override
	public List<Modulo> listarModulos() {
		return moduloDao.listarModulos();
	}

	@Override
	public List<Modulo> listarModulosPorNombre(String nombreModulo) {
		return moduloDao.listarModulosPorNombre(nombreModulo);
	}

	@Override
	public Modulo obtenerModulo(long idModulo) {
		return moduloDao.find(idModulo);
	}

	@Override
	public Modulo agregarProfesor(long idModulo, long idProfesor) {
		
		Modulo m = moduloDao.find(idModulo);
		
		for (Profesor p: m.getProfesores()){
			if (p.getIdProfesor() == idProfesor) {
				return null;
			}
		}
		Profesor profesor =ProfesorDao.find(idProfesor);
		Modulo modulo = moduloDao.agregarProfesor(idModulo, profesor);
		
		return modulo;
	}

	@Override
	public Modulo eliminarProfesor(long idModulo, long idProfesor) {

		Profesor profesor =ProfesorDao.find(idProfesor);
		Modulo m = moduloDao.find(idModulo);

		for (Profesor p: m.getProfesores()){
			if (p.getIdProfesor() == idProfesor) {
				return moduloDao.eliminarProfesor(idModulo, profesor);
			}
		}
		return null;
	}

}
