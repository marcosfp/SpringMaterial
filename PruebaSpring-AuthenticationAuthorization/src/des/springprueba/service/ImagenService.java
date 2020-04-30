package des.springprueba.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import des.springprueba.dao.ImagenDao;
import des.springprueba.dao.ProfesorDao;
import des.springprueba.entity.Imagen;
import des.springprueba.entity.Profesor;

@Transactional
@Service
public class ImagenService {

	@Autowired
	private ImagenDao imgDao;

	@Autowired
	private ProfesorDao profesorDao;

	public int saveImage(Imagen img) {
		try {
			imgDao.save(img);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Imagen getImages(Long id) {
		Optional findById = imgDao.findById(id);
		if (findById.isPresent()) {
			Imagen getImageDetails = (Imagen) findById.get();
			return getImageDetails;
		} else {
			return null;
		}
	}

	public Boolean actualizaFotoUsuario(long idProfesor, MultipartFile file) {

		Profesor p = profesorDao.find(idProfesor);

		if (p == null) return false;
		try {
			byte[] image = file.getBytes();
            
			if (p.getImagen()==null) {
				Imagen img = new Imagen("foto", image);
    			p.setImagen(img);
    			img.setProfesor(p);
    			imgDao.save(img);
			}else {
				Imagen i = p.getImagen();
				i.setImagen(image);
				imgDao.save(i);
			}
			
//            
////            if (p.getImagen()!= null) {
//    			p.setImagen(img);
//    			img.setProfesor(p);
////    			profesorDao.update(p);
//    			imgDao.save(img);
//    		}
            
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
		

		return true;
	}

}