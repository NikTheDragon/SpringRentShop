package by.shop.rent.dao.impl;

import by.shop.rent.beans.Item;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipmentDAOImpl implements EquipmentDAO {
	private final static Logger LOGGER = Logger.getLogger(EquipmentDAOImpl.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDtaSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	Item item;
	
	final String FORM_EQUIPMENT_DATA = "SELECT e.*, r.client_id,types.folder FROM equipment e LEFT OUTER JOIN rented_items r ON e.id=r.equipment_id,types WHERE e.type=types.type AND types.folder LIKE ?";
	final String FORM_CATEGORY_LIST = "SELECT DISTINCT folder FROM types";
	final String FORM_CART_LIST = "SELECT * FROM equipment WHERE id=?";
	final String ADD_RENTED_ITEM = "INSERT INTO rented_items (client_id, equipment_id, date, length) VALUES (?,?,?,?)";
	final String USER_ITEMS = "SELECT * FROM equipment, rented_items WHERE equipment.id=rented_items.equipment_id AND rented_items.client_id=?";
	final String REMOVE_RENTED_ITEM = "DELETE FROM rented_items WHERE client_id=? AND equipment_id=?";

   /* @Override
    public void removeRentedEquipment(int clientId, int equipmentId) throws DAOException {
        final int CLIENTS_ID = 1;
        final int EQUIPMENT_ID = 2;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(REMOVE_RENTED_ITEM);
            ps.setInt(CLIENTS_ID, clientId);
            ps.setInt(EQUIPMENT_ID, equipmentId);
            ps.executeUpdate();

        } catch (SQLException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new DAOException("Exception in removeRentedItem", e);
        }
    }*/

    @Override
    public List<Item> getUserEquipment(int clientId) throws DAOException {
        final int ID = 1;
        final int TYPE = 2;
        final int NAME = 3;
        final int DESCRIPTION = 4;
        final int MANUFACTURER = 5;
        final int COST = 6;
        final int IMG = 7;
        
        List<Item> userItems = jdbcTemplate.query(USER_ITEMS, new Object[] { clientId }, new RowMapper<Item>() {

			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				Item item = new Item();
				
				item.setId(rs.getInt(ID));
				item.setName(rs.getString(NAME));
				item.setType(rs.getString(TYPE));
				item.setDescription(rs.getString(DESCRIPTION));
				item.setManufacturer(rs.getString(MANUFACTURER));
				item.setPrice(rs.getInt(COST));
				item.setImg(rs.getString(IMG));
				
				return item;
			}
		});

		return userItems;
	}

 /*   @Override
    public void addRentedEquipment(int clientId, int equipmentId, int days) throws DAOException, EquipmentAlreadyExistsException {
        final int CLIENT_ID = 1;
        final int EQUIPMENT_ID = 2;
        final int DATE = 3;
        final int LENGTH = 4;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD_RENTED_ITEM);
            ps.setInt(CLIENT_ID, clientId);
            ps.setInt(EQUIPMENT_ID, equipmentId);
            ps.setDate(DATE, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(LENGTH, days);
            ps.executeUpdate();

		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new EquipmentAlreadyExistsException("duplicated primary key found. Item ID: " + equipmentId);
			}
			LOGGER.error(e.getMessage(), e);
            throw new DAOException("Exception in addRentedItem", e);
        }
    }*/

    @Override
    public List<Item> findCartEquipment(List<String> cart) throws DAOException {
        final int ID = 1;
        final int TYPE = 2;
        final int NAME = 3;
        final int DESCRIPTION = 4;
        final int MANUFACTURER = 5;
        final int COST = 6;
        final int IMG = 7;
		
        List<Item> items = new ArrayList<>();
        
		try {
			for (String id : cart) {

				item = jdbcTemplate.queryForObject(FORM_CART_LIST, new Object[] { id }, new RowMapper<Item>() {

					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						Item item = new Item();

						item.setId(rs.getInt(ID));
						item.setName(rs.getString(NAME));
						item.setType(rs.getString(TYPE));
						item.setDescription(rs.getString(DESCRIPTION));
						item.setManufacturer(rs.getString(MANUFACTURER));
						item.setPrice(rs.getInt(COST));
						item.setImg(rs.getString(IMG));

						return item;
					}
				});
				items.add(item);
			}
			return items;

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in findCartItems", e);
		}
	}

	@Override
	public List<Item> formEquipmentList(String category) throws DAOException {
		final int ID = 1;
		final int TYPE = 2;
		final int NAME = 3;
		final int DESCRIPTION = 4;
		final int MANUFACTURER = 5;
		final int COST = 6;
		final int IMG = 7;
		final int CLIENT_ID = 8;
		
		List<Item> items = jdbcTemplate.query(FORM_EQUIPMENT_DATA, new Object[] { category }, new RowMapper<Item>() {

			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				Item item = new Item();
				
				item.setId(rs.getInt(ID));
				item.setName(rs.getString(NAME));
				item.setType(rs.getString(TYPE));
				item.setDescription(rs.getString(DESCRIPTION));
				item.setManufacturer(rs.getString(MANUFACTURER));
				item.setPrice(rs.getInt(COST));
				item.setImg(rs.getString(IMG));
				item.setOwner(rs.getInt(CLIENT_ID));
				
				return item;
			}
		});

		return items;
	}

	@Override
	public List<String> formCategoryElementsList() throws DAOException {
		List<String> categoryElements = new ArrayList<>();

		categoryElements = jdbcTemplate.queryForList(FORM_CATEGORY_LIST, String.class);

		return categoryElements;
	}
}
