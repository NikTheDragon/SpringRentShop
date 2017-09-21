package by.shop.rent.service;

import java.util.List;

import by.shop.rent.beans.Item;
import by.shop.rent.service.exception.ServiceException;

public interface EquipmentService {
	//boolean isInCart(List<Integer> cart, int id);
	void rentItem(String userId, String itemId, String days) throws ServiceException;
	List<Item> formCartEquipmentList(List<String> cart) throws ServiceException;
	List<String> formCategoryElementList() throws ServiceException;
	List<Item> formEquipmentList(String ctegory) throws ServiceException;
	List<Item> formUserEquipmentList(int clientId) throws ServiceException;
	void returnRentedEquipment(String clientId, String equipmentId) throws ServiceException;
}
