package by.shop.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.shop.rent.beans.User;
import by.shop.rent.dao.ClientDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.service.ClientService;
import by.shop.rent.service.exception.LoginException;
import by.shop.rent.service.exception.ServiceException;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDAO clientDAO;
	
	@Override
	public User getUserInfo(String login) throws ServiceException, LoginException {

		try {
			User userInfo = clientDAO.getUserInfo(login);
			if (userInfo == null) {
				throw new LoginException("wrong login or password");
			}
			return userInfo;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public void checkLogin(String login) throws ServiceException, LoginException {


		try {
			System.out.println("in client service");
			if (clientDAO.isClientExist(login)) {
				throw new LoginException("login already in use");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public void registerClient () throws ServiceException {
		try {
			clientDAO.addNewClient();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public void updateUserInfo(User user) throws ServiceException {
		try {
			clientDAO.updateUserData(user);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
