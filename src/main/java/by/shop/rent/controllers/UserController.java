package by.shop.rent.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
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
import by.shop.rent.beans.Equipment;
import by.shop.rent.beans.User;
import by.shop.rent.service.ClientService;
import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;

@Controller
@EnableCaching
@SessionAttributes("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	
	@Resource(name = "cart") 
	
	@Autowired
	Cart cart;
	
	List<String> equipmentCategoryList;
	List<Equipment> equipmentList;

	@Autowired
	ClientService clientService;

	@Autowired
	EquipmentService equipmentService;

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
				equipmentCategoryList = equipmentService.formCategoryElementList();
				equipmentList = equipmentService.formEquipmentList(line);

				model.addAttribute("user", user);
				model.addAttribute("category", equipmentCategoryList);
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
			List<Equipment> clientItems = equipmentService.formUserEquipmentList(user.getId());
			model.addAttribute("items", clientItems);

		} catch (Exception e) {
		}

		return "user_items";
	}

	@RequestMapping(value = "user/add_to_cart", method = { RequestMethod.GET, RequestMethod.POST })
	public String addToCart(@RequestParam("itemID") String itemID, Model model, Locale locale) {

		cart.addEquipment(itemID);

		model.addAttribute("user", user);
		model.addAttribute("category", equipmentCategoryList);
		model.addAttribute("equipment", equipmentList);

		return "user_page";
	}

	@RequestMapping(value = "user/user_cart", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCart(Model model, Locale locale) {

		try {
			long startTime=System.currentTimeMillis();
			System.out.println("begin cart.");
			equipmentService.formCartEquipmentList();
			System.out.println((System.currentTimeMillis()-startTime)+" end cart.");
			model.addAttribute("cart", cart.getCart());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return "user_cart";
	}

	@RequestMapping(value = "user/delete_item", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteItem(@RequestParam("itemID") String itemID, Model model, Locale locale) {

		cart.removeEquipment(itemID);

		try {
			equipmentService.formCartEquipmentList();
			model.addAttribute("cart", cart.getCart());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return "user_cart";
	}

	@RequestMapping(value = "user/rent_item", method = { RequestMethod.GET, RequestMethod.POST })
	public String rentItem(@RequestParam Map<String, String> requestParams, Model model, Locale locale) {
		String itemID = requestParams.get("itemID");
		String userID = requestParams.get("userID");
		String days = requestParams.get("days");
		
		try {
			equipmentService.rentItem(userID, itemID, days);
			model.addAttribute("cart", cart.getCart());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return "user_cart";
	}
	
	@RequestMapping(value="user/return_equipment",  method = { RequestMethod.GET, RequestMethod.POST })
	public String returnEquipment(@RequestParam Map<String, String> requestParams, Model model, Locale locale){
		String equipmentID = requestParams.get("equipmentID");
		String clientID = requestParams.get("clientID");
		
		try {
			equipmentService.returnRentedEquipment(clientID, equipmentID);
			List<Equipment> clientEquipment = equipmentService.formUserEquipmentList(user.getId());
			model.addAttribute("items", clientEquipment);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "user_items";
	}
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("items");
    }
}
