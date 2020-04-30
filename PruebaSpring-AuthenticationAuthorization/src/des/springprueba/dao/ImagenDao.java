package des.springprueba.dao;

import org.springframework.data.repository.CrudRepository;

import des.springprueba.entity.Imagen;

public interface ImagenDao extends CrudRepository<Imagen, Long> {

}
