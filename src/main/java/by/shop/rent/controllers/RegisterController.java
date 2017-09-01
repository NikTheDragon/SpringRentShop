package by.shop.rent.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import by.shop.rent.beans.User;
import by.shop.rent.service.ClientService;
import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;
import by.shop.rent.service.factory.ServiceFactory;

@Controller
@SessionAttributes("user")
public class RegisterController {
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
	@Autowired
	ClientService clientService = serviceFactory.getClientServiceImpl();

	@Autowired
	User user;
	
	@RequestMapping(value = "/reg_client", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register(Locale locale) {

		return new ModelAndView("reg_client", "user", user);
	}

	@RequestMapping(value = "/reg_new_client", method = { RequestMethod.GET, RequestMethod.POST })
	public String showUser(@Valid @ModelAttribute("user") @Autowired User user, BindingResult bindingResult,
			Locale locale, Model model) {

		if (bindingResult.hasErrors()) {
			return "reg_client";
		}

		try {
			clientService.checkLogin(user.getLogin());
			clientService.registerClient();
			bindingResult.rejectValue("message", "registration.done", "reg done");
		} catch (LoginException e) {
			bindingResult.rejectValue("message", "registration.invalid.login", "login taken");
		} catch (ServiceException e) {
			model.addAttribute("message", e.getMessage());
		}

		return "reg_client";
	}

}
