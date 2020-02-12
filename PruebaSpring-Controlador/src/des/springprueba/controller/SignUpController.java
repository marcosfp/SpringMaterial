package des.springprueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.springprueba.model.Account;

@Controller
public class SignUpController {

	@RequestMapping(method = RequestMethod.GET,value = "signup" )
	public String signUpView() {
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "signup")
	public ModelAndView handleSignUp(@RequestParam("username") String username, 
									@RequestParam("password") String password) {

		ModelAndView mav = new ModelAndView();
		if (username == null || password == null) {
			mav.addObject("exception", "Username or password are empty.");
			mav.setViewName("signup");
		}
		Account account = new Account(username, password);
		mav.addObject("account", account);
		mav.setViewName("profile");
		return mav;
	}
}