package by.shop.rent.service;

import by.shop.rent.beans.User;
import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;

public interface ClientService {
	User getUserInfo(String login) throws ServiceException, LoginException;

	//void checkEmptyFields(ClientData clientData) throws EmptyFieldsException;

	void checkLogin(String login) throws ServiceException, LoginException;

	void registerClient() throws ServiceException;
}
