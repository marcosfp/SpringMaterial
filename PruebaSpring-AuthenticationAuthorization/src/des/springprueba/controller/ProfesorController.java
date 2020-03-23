package des.springprueba.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import des.springprueba.entity.Email;
import des.springprueba.entity.Profesor;
import des.springprueba.service.ProfesorService;

@Controller
@RequestMapping(value = "/profesor")
public class ProfesorController {

	@Autowired
	ProfesorService profesorService;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listarPorfesores() {

		ModelAndView mav = new ModelAndView();

		List<Profesor> lProfesor = profesorService.listarProfesores();

		mav.addObject("profesores", lProfesor);
		mav.setViewName("profesor_lista");
		return mav;
	}

//	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/myprofile")
	public String perfilPersonal(HttpServletRequest request) {
		
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("idUsuario") != null)
        	return "redirect:/profesor/perfil/" + session.getAttribute("idUsuario");
        else 
        	return"redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/perfil/{id}")
	public ModelAndView perfilProfesor(@PathVariable("id") long idProfesor,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Profesor profesor = profesorService.obtenerProfesor(idProfesor);

		long idSession= (long) request.getSession().getAttribute("idUsuario");
		
		Boolean propietario= idSession == idProfesor;
		
		mav.addObject("propietario", propietario);
		mav.addObject("profesor", profesor);
		mav.setViewName("profesor_perfil");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/anadir_email/{id}")
	public String anadirEmail(@PathVariable("id") long idProfesor, HttpServletRequest request) {

		String direccion_email = request.getParameter("nuevoemail");

		Email email = new Email();
		email.setDireccionEmail(direccion_email);

		Profesor profesor = profesorService.anadirEmail(idProfesor, email);

		return "redirect:/profesor/perfil/" + idProfesor;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/eliminar_email/{idProfesor}/{idEmail}")
	public String eliminarEmail(@PathVariable("idEmail") long idEmail, @PathVariable("idProfesor") long idProfesor) {

		Email email = new Email();
		email.setIdEmail(idEmail);

		profesorService.eliminarEmail(idProfesor, email);

		return "redirect:/profesor/perfil/" + idProfesor;
	}
}
