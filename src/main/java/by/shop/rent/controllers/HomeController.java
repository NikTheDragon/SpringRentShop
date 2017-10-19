package by.shop.rent.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.exception.ServiceException;

@Controller
public class HomeController {
	
	@Autowired
	EquipmentService equipmentService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Locale locale, Model model) {

		return "index";
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Locale locale, Model model) {

		return "index";
	}

	@RequestMapping(value = "/map", method = { RequestMethod.GET, RequestMethod.POST })
	public String map(Locale locale, Model model) {

		return "map";
	}
	
	@RequestMapping(value = "/about", method = { RequestMethod.GET, RequestMethod.POST })
	public String about(Locale locale, Model model) {

		return "about";
	}
	
	@RequestMapping(value = "/catalogue", method = { RequestMethod.GET, RequestMethod.POST })
	public String catalogue(@RequestParam(value = "line", defaultValue = "%", required = false) String line, Locale locale, Model model) {
		try {
			model.addAttribute("category", equipmentService.formCategoryElementList());
			model.addAttribute("equipment", equipmentService.formEquipmentList(line));
			
		} catch (ServiceException e) {
			model.addAttribute("message", e.getCause());
			logger.error(e.getMessage(), e);
			
			return "error_page";
		}

		return "catalogue";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Locale locale, Model model) {
		model.addAttribute("message", "login_incorrect");
		
		return "index";
	}
}
