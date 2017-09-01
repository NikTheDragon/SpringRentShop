package by.shop.rent.dao.impl;

import by.shop.rent.beans.ClientData;
import by.shop.rent.beans.User;
import by.shop.rent.dao.ClientDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.objects.Info;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements ClientDAO {

	private final static Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDtaSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	User user;

	final String COUNT_CLIENTS = "SELECT COUNT(*) FROM clients WHERE login = ?";
	// final String GET_CLIENT_INFORMATION = "SELECT * FROM clients WHERE login=? AND password=?";
	final String ADD_CLIENT = "INSERT INTO clientsss (name,surname,email,phone,login,password,status) VALUES (?,?,?,?,?,?,?)";

	@Override
	public boolean isClientExist(String login) throws DAOException {
		System.out.println("in clients dao");
		System.out.println("login=" + login);

		int clients = jdbcTemplate.queryForObject(COUNT_CLIENTS, Integer.class, login);
		if (clients == 0) {
			return false;
		}
		System.out.println("user="+user);

		return true;
	}

	/*@Override
	public ClientData formClientData(String login, String password) throws DAOException {
		final int LOGIN = 1;
		final int PASSWORD = 2;

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(GET_CLIENT_INFORMATION);
			ps.setString(LOGIN, login);
			ps.setString(PASSWORD, password);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					return new ClientData.Builder().id(rs.getInt(1)).name(rs.getString(2)).surname(rs.getString(3))
							.email(rs.getString(4)).phone(rs.getString(5)).login(rs.getString(6))
							.password(rs.getString(7)).status(rs.getString(8)).build();
				}
			}
			return null;

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in formClientData", e);
		}
	}
*/
	@Override
	public void addNewClient() throws DAOException {
		System.out.println("adding new client");
		try {
			jdbcTemplate.update(ADD_CLIENT, user.getName(), user.getSurname(), user.getEmail(), user.getPhone(),
					user.getLogin(), user.getPassword(), "user");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DAOException("Exception in addNewClient", e);
		}
	}

}