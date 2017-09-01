package by.shop.rent.dao;

import by.shop.rent.dao.exception.DAOException;

public interface ClientDAO {

	//ClientData formClientData(String login, String password) throws DAOException;
	
	boolean isClientExist(String login) throws DAOException;
	
	void addNewClient() throws DAOException;
}