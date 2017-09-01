package by.shop.rent.service;

import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;

public interface ClientService {
	//ClientData getClientData(String login, String password) throws ServiceException, LoginException;

	//void checkEmptyFields(ClientData clientData) throws EmptyFieldsException;

	void checkLogin(String login) throws ServiceException, LoginException;

	void registerClient() throws ServiceException;
}
