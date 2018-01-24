package by.shop.rent.service;

import by.shop.rent.beans.User;
import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;

public interface ClientService {
	User getUserInfo(String login) throws ServiceException, LoginException;
	void checkLogin(String login) throws ServiceException, LoginException;
	void registerClient() throws ServiceException;
	void updateUserInfo(User user) throws ServiceException;
}
