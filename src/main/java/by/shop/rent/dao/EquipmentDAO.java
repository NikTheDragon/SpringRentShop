package by.shop.rent.dao;

import java.util.List;

import by.shop.rent.beans.Item;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;

public interface EquipmentDAO {

	void removeRentedEquipment(int clientId, int equipmentId) throws DAOException;
	List<Item> findClientEquipment (int clientId) throws DAOException;
	void addRentedEquipment(int clientId, int equipmentId, int days) throws DAOException, EquipmentAlreadyExistsException;
	List<Item> findCartEquipment(List<Integer> cart) throws DAOException;
	List<Item> formEquipmentList(String folder) throws DAOException;
	List<String> formFolderElementsList() throws DAOException;
	
}
