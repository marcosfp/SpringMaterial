package des.springprueba.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springprueba.entity.Modulo;
import des.springprueba.entity.Profesor;

@Repository
@Component("ModuloDao")
public class ModuloDaoImpl extends GenericDaoImpl<Modulo> implements ModuloDao {

	@Override
	public List<Modulo> listarModulosPorNombre(String nombreModulo) {
		Query query = this.em
                .createQuery("FROM Modulo u where u.nombreModulo= :nombre");
        query.setParameter("nombre", nombreModulo);
        List<Modulo> lModulo = query.getResultList();
        
        if (lModulo != null ) {
            return lModulo;
        }
		return null;
	}

	@Override
	public List<Modulo> listarModulos() {
		Query query = this.em
                .createQuery("FROM Modulo");
        List<Modulo> lModulo = query.getResultList();
        
        if (lModulo != null ) {
            return lModulo;
        }
		return null;
	}

	@Override
	public Modulo agregarProfesor(long idModulo, Profesor profesor) {

		Modulo modulo = this.find(idModulo);
		modulo.addProfesor(profesor);
		this.em.merge(modulo);
		this.em.refresh(modulo);
		this.em.flush();
		this.em.clear();
		
		return modulo;
	}

	@Override
	public Modulo eliminarProfesor(long idModulo, Profesor profesor) {
		Modulo modulo = this.find(idModulo);
		modulo.deleteProfesor(profesor);
		
		return modulo;
	}

	
	
	
}
