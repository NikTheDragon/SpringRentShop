package by.shop.rent.service.impl;

import java.util.List;

import by.shop.rent.beans.Item;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;
import by.shop.rent.dao.factory.DAOFactory;
import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.exception.ServiceException;

public class EquipmentServiceImpl implements EquipmentService {
	
	@Override
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
	}
	
	@Override
	public List<String> formCategoryElementList() throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			List<String> folderList = equipmentDAO.formFolderElementsList();
			return folderList;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Item> formEquipmentList(String folder) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			List<Item> equipmentList = equipmentDAO.formEquipmentList(folder);
			return equipmentList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Item> formClientEquipment(int clientId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			return equipmentDAO.findClientEquipment(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}
	
	@Override
	public void removeRentedEquipment(int clientId, int equipmentId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();
		
		try {
			equipmentDAO.removeRentedEquipment(clientId, equipmentId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
