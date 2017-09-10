package by.shop.rent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.shop.rent.beans.Item;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;
import by.shop.rent.dao.factory.DAOFactory;
import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.exception.ServiceException;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	DAOFactory daoObjectFactory = DAOFactory.getInstance();
	
	@Autowired
	EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();
	
	
	/*@Override
	public boolean isInCart(List<Integer> cart, int id) {
		for (Integer cartIDs : cart) {
			if (cartIDs == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addRentedItem(int clientId, int equipmentId, int days) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			equipmentDAO.addRentedEquipment(clientId, equipmentId, days);
		} catch (EquipmentAlreadyExistsException e) {
			System.out.println("duplicate primary keys found");
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Item> formCartEquipment (List<Integer> cart) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();
				
		try {
			return equipmentDAO.findCartEquipment(cart);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
	
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
	public List<Item> formEquipmentList(String category) throws ServiceException {
		try {
			List<Item> equipmentList = equipmentDAO.formEquipmentList(category);
			return equipmentList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Item> formUserEquipmentList(int clientId) throws ServiceException {
		try {
			return equipmentDAO.getUserEquipment(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}
	
	/*@Override
	public void removeRentedEquipment(int clientId, int equipmentId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();
		
		try {
			equipmentDAO.removeRentedEquipment(clientId, equipmentId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/

}
