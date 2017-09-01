package by.shop.rent.dao.factory;

import by.shop.rent.dao.ClientDAO;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.impl.ClientDAOImpl;
import by.shop.rent.dao.impl.EquipmentDAOImpl;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public final class DAOFactory {

	private final static String DEFAULT = "default.properties";
	private final static String URL = "jdbc.url";
	private final static String USERNAME = "jdbc.username";
	private final static String PASSWORD = "jdbc.password";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static Logger LOGGER = Logger.getLogger(DAOFactory.class);
	private final static DAOFactory instance = new DAOFactory();
	private final ClientDAO clientDAO = new ClientDAOImpl();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public ClientDAO getClientDAOImpl() {
		return new ClientDAOImpl();
	}

	public EquipmentDAO getEquipmentDAOImpl() {
		return new EquipmentDAOImpl();
	}

}
