package by.shop.rent.dao.factory;

import by.shop.rent.dao.ClientDAO;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.impl.ClientDAOImpl;
import by.shop.rent.dao.impl.EquipmentDAOImpl;

public final class DAOFactory {

	private final ClientDAO clientDAO = new ClientDAOImpl();
	private final EquipmentDAO equipmentDAO = new EquipmentDAOImpl();

	private DAOFactory() {
	}

	public ClientDAO getClientDAOImpl() {
		return clientDAO;
	}

	public EquipmentDAO getEquipmentDAOImpl() {
		return equipmentDAO;
	}

}
