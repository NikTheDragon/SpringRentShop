package by.shop.rent.dao;

import by.shop.rent.beans.User;
import by.shop.rent.dao.exception.DAOException;

public interface ClientDAO {
	User getUserInfo(String login) throws DAOException;
	boolean isClientExist(String login) throws DAOException;
	void addNewClient() throws DAOException;
	void updateUserData(User user) throws DAOException;
}