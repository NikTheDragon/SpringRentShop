package by.shop.rent.service;

import java.util.List;

import by.shop.rent.beans.Item;
import by.shop.rent.service.exception.ServiceException;

public interface EquipmentService {
	//boolean isInCart(List<Integer> cart, int id);
	//void addRentedItem(int clientId, int equipmentId, int days) throws ServiceException;
	//List<Item> formCartEquipment(List<Integer> cart) throws ServiceException;
	List<String> formCategoryElementList() throws ServiceException;
	List<Item> formEquipmentList(String ctegory) throws ServiceException;
	//List<Item> formClientEquipment(int clientId) throws ServiceException;
	//void removeRentedEquipment(int clientId, int equipmentId) throws ServiceException;
}
