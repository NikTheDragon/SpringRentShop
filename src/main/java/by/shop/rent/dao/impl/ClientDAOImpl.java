package by.shop.rent.dao.impl;

import by.shop.rent.beans.User;
import by.shop.rent.dao.ClientDAO;
import by.shop.rent.dao.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	final String GET_CLIENT_INFORMATION = "SELECT * FROM clients WHERE login=?";
	final String ADD_CLIENT = "INSERT INTO clients (name,surname,email,phone,login,password,status) VALUES (?,?,?,?,?,?,?)";
	final String UPDATE_USER = "UPDATE clients SET name=?,surname=?,email=?,phone=?,login=?,password=? WHERE id=?";

	@Override
	public boolean isClientExist(String login) throws DAOException {
		int clients = jdbcTemplate.queryForObject(COUNT_CLIENTS, Integer.class, login);
		if (clients == 0) {
			return false;
		}
		System.out.println("user=" + user);

		return true;
	}

	@Override
	public User getUserInfo(String login) throws DAOException {
		List<User> list;
		list = jdbcTemplate.query(GET_CLIENT_INFORMATION, new Object[] { login }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				final int ID = 1;
				final int NAME = 2;
				final int SURNAME = 3;
				final int EMAIL = 4;
				final int PHONE = 5;
				final int LOGIN = 6;
				final int PASSWORD = 7;
				final int STATUS = 8;

				User user = new User();

				user.setId(rs.getInt(ID));
				user.setName(rs.getString(NAME));
				user.setSurname(rs.getString(SURNAME));
				user.setEmail(rs.getString(EMAIL));
				user.setPhone(rs.getString(PHONE));
				user.setLogin(rs.getString(LOGIN));
				user.setPassword(rs.getString(PASSWORD));
				user.setStatus(rs.getString(STATUS));
				
				return user;
			}
		});

		return list.get(0);
	}

	@Override
	public void addNewClient() throws DAOException {
		System.out.println("adding new client");
		try {
			jdbcTemplate.update(ADD_CLIENT, user.getName(), user.getSurname(), user.getEmail(), user.getPhone(),
					user.getLogin(), user.getPassword(), "ROLE_USER");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DAOException("Exception in addNewClient", e);
		}
	}
	
	@Override
	public void updateUserData(User user) throws DAOException {
		System.out.println("updating client "+user.getName()+" id "+user.getId());
		try {
			jdbcTemplate.update(UPDATE_USER, user.getName(), user.getSurname(), user.getEmail(), user.getPhone(),
					user.getLogin(), user.getPassword(), user.getId());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			throw new DAOException("Exception in updateUser", e);
		}
	}

}