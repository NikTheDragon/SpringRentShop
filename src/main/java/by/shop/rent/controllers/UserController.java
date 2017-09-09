package by.shop.rent.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import by.shop.rent.beans.Item;
import by.shop.rent.beans.User;
import by.shop.rent.service.ClientService;
import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;
import by.shop.rent.service.factory.ServiceFactory;

@Controller
@SessionAttributes("user")
public class UserController {
	ServiceFactory serviceFactory = ServiceFactory.getInstance();

	@Autowired
	ClientService clientService = serviceFactory.getClientServiceImpl();

	@Autowired
	EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();

	@Autowired
	User user;

	@RequestMapping(value = "/user/user_page", method = { RequestMethod.GET, RequestMethod.POST })
	public String userPage(@RequestParam(value = "line", defaultValue="%", required = false) String line, Model model, Locale locale) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println("user=" + userDetail.getUsername());

			try {
				user = clientService.getUserInfo(userDetail.getUsername());
				List<String> category = equipmentService.formCategoryElementList();
				List<Item> equipmentList = equipmentService.formEquipmentList(line);

				model.addAttribute("user", user);
				model.addAttribute("category", category);
				model.addAttribute("equipment", equipmentList);

			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (LoginException e) {
				e.printStackTrace();
			}
		}

		return "user_page";
	}
}
