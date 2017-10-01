package by.shop.rent.service;

import java.util.List;

import by.shop.rent.beans.Equipment;
import by.shop.rent.service.exception.ServiceException;

public interface EquipmentService {
	void rentItem(String userId, String itemId, String days) throws ServiceException;
	//List<Equipment> formCartEquipmentList(List<String> cart) throws ServiceException;
	void formCartEquipmentList() throws ServiceException;
	List<String> formCategoryElementList() throws ServiceException;
	List<Equipment> formEquipmentList(String category) throws ServiceException;
	List<Equipment> formUserEquipmentList(int clientId) throws ServiceException;
	void returnRentedEquipment(String clientId, String equipmentId) throws ServiceException;
}
