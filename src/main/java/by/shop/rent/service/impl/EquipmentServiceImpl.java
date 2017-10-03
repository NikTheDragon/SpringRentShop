package by.shop.rent.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.shop.rent.beans.Cart;
import by.shop.rent.beans.Equipment;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;
import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.exception.ServiceException;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	Cart cart;
	
	@Autowired
	EquipmentDAO equipmentDAO;

	@Override
	public void rentItem(String userID, String itemID, String days) throws ServiceException {
		try {
			equipmentDAO.addRentedEquipment(userID, itemID, days);
		} catch (EquipmentAlreadyExistsException e) {
			System.out.println("duplicate primary keys found");
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public void formCartEquipmentList () throws ServiceException {
		try {
			for (String id: cart.getIdCart()) {
				Equipment eq = equipmentDAO.findCartEquipment(id);
				cart.addEquipment(eq);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<String> formCategoryElementList() throws ServiceException {
		try {
			List<String> categoryList = equipmentDAO.formCategoryElementsList();
			return categoryList;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Equipment> formEquipmentList(String category) throws ServiceException {
		try {
			List<Equipment> equipmentList = equipmentDAO.formEquipmentList(category);
			return equipmentList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Equipment> formUserEquipmentList(int clientId) throws ServiceException {
		try {
			return equipmentDAO.getUserEquipment(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}
	
	@Override
	public void returnRentedEquipment(String clientId, String equipmentId) throws ServiceException {
		try {
			equipmentDAO.returnRentedEquipment(clientId, equipmentId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
