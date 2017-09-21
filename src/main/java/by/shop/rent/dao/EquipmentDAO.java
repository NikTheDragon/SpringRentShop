package by.shop.rent.dao;

import java.util.List;

import by.shop.rent.beans.Item;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;

public interface EquipmentDAO {

	void returnRentedEquipment(String clientId, String equipmentId) throws DAOException;
	List<Item> getUserEquipment (int clientId) throws DAOException;
	void addRentedEquipment(String clientId, String equipmentId, String days) throws DAOException, EquipmentAlreadyExistsException;
	List<Item> findCartEquipment(List<String> cart) throws DAOException;
	List<Item> formEquipmentList(String category) throws DAOException;
	List<String> formCategoryElementsList() throws DAOException;
	
}
