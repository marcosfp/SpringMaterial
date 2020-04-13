package des.springprueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import des.springprueba.dto.ProfesorDto;
import des.springprueba.entity.Modulo;
import des.springprueba.service.ModuloService;

@Controller
@RequestMapping(value = "/modulo")
public class ModuloController {

	@Autowired
	ModuloService moduloService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView perfilProfesor(@PathVariable("id") long idModulo) {

		ModelAndView mav = new ModelAndView();

		Modulo modulos = moduloService.obtenerModulo(idModulo);

		mav.addObject("modulo", modulos);
		mav.setViewName("modulo_perfil");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/agregarprofesor/{id}")
	public @ResponseBody ResponseEntity agregarProfesor(@PathVariable("id") long idModulo,@RequestBody ProfesorDto profesorDto) {

		Modulo modulo =moduloService.agregarProfesor(idModulo, profesorDto.getIdProfesor());
		
		if (modulo ==null) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/eliminarprofesor/{idModulo}/{idProfesor}")
	public @ResponseBody ResponseEntity eliminarProfesor(@PathVariable("idModulo") long idModulo, @PathVariable("idProfesor") long idProfesor) {

		Modulo modulo =moduloService.eliminarProfesor(idModulo, idProfesor);
		
		if (modulo ==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity(HttpStatus.OK);
	}

}
