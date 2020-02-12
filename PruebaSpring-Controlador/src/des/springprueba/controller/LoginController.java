package des.springprueba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import des.springprueba.model.Account;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleLogin(HttpServletRequest request, HttpSession session)  {

		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.equals("Marcos") && password.equals("123456")) {
			
			Account account = new Account(username, password);
			mav.addObject("account", account);
			mav.setViewName("profile");
			session.setAttribute("accountSession", "Marcos");
			return mav;
		}
		mav.addObject("exception", "Este usuario no existe en nuestro sistema.");
		mav.setViewName("singup");
		return mav;
	}

}
