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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.shop.rent.beans.Cart;
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
	Cart cart = new Cart();
	List<String> categoryElementsList;
	List<Item> equipmentList;
	List<Item> cartList;

	@Autowired
	ClientService clientService = serviceFactory.getClientServiceImpl();

	@Autowired
	EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();

	@Autowired
	User user;

	@RequestMapping(value = { "/user/user_page", "/user/index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String userPage(@RequestParam(value = "line", defaultValue = "%", required = false) String line, Model model,
			Locale locale) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println("user=" + userDetail.getUsername());

			try {
				user = clientService.getUserInfo(userDetail.getUsername());
				categoryElementsList = equipmentService.formCategoryElementList();
				equipmentList = equipmentService.formEquipmentList(line);

				model.addAttribute("user", user);
				model.addAttribute("category", categoryElementsList);
				model.addAttribute("equipment", equipmentList);

			} catch (ServiceException | LoginException e) {
				e.printStackTrace();
			}
		}

		return "user_page";
	}

	@RequestMapping(value = "/user/user_items", method = { RequestMethod.GET, RequestMethod.POST })
	public String userItems(Model model, Locale locale) {
		try {
			List<Item> clientItems = equipmentService.formUserEquipmentList(user.getId());
			model.addAttribute("items", clientItems);

		} catch (Exception e) {
		}

		return "user_items";
	}

	@RequestMapping(value = "user/add_to_cart", method = { RequestMethod.GET, RequestMethod.POST })
	public String addToCart(@RequestParam("itemID") String itemID, Model model, Locale locale) {

		cart.addItem(itemID);

		model.addAttribute("user", user);
		model.addAttribute("category", categoryElementsList);
		model.addAttribute("equipment", equipmentList);

		return "user_page";
	}

	@RequestMapping(value = "user/user_cart", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCart(Model model, Locale locale) {

		try {
			cartList = equipmentService.formCartEquipmentList(cart.getCart());
			model.addAttribute("cart", cartList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return "user_cart";
	}

	@RequestMapping(value = "user/delete_item", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteItem(@RequestParam("itemID") String itemID, Model model, Locale locale) {

		cart.removeItem(itemID);
		
		try {
			cartList = equipmentService.formCartEquipmentList(cart.getCart());
			model.addAttribute("cart", cartList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "user_cart";
	}
}
