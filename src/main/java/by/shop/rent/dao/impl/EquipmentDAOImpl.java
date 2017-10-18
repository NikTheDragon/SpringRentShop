package by.shop.rent.dao.impl;

import by.shop.rent.beans.Equipment;
import by.shop.rent.dao.EquipmentDAO;
import by.shop.rent.dao.exception.DAOException;
import by.shop.rent.dao.exception.EquipmentAlreadyExistsException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

	//@Autowired
	//Equipment equipment;
	
	final String FORM_EQUIPMENT_DATA = "SELECT e.*, r.client_id,types.folder FROM equipment e LEFT OUTER JOIN rented_items r ON e.id=r.equipment_id,types WHERE e.type=types.type AND types.folder LIKE ?";
	final String FORM_CATEGORY_LIST = "SELECT DISTINCT folder FROM types";
	final String FORM_CART_LIST = "SELECT * FROM equipment WHERE id=?";
	final String ADD_RENTED_ITEM = "INSERT INTO rented_items (client_id, equipment_id, date, length) VALUES (?,?,?,?)";
	final String USER_ITEMS = "SELECT * FROM equipment, rented_items WHERE equipment.id=rented_items.equipment_id AND rented_items.client_id=?";
	final String REMOVE_RENTED_ITEM = "DELETE FROM rented_items WHERE client_id=? AND equipment_id=?";

    @Override
    public void returnRentedEquipment(String clientID, String equipmentID) throws DAOException {
        try {
        	jdbcTemplate.update(REMOVE_RENTED_ITEM, clientID, equipmentID);

        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
            throw new DAOException("Exception in removeRentedItem", e);
        }
    }

    @Override
    public List<Equipment> getUserEquipment(int clientId) throws DAOException {
        List<Equipment> userItems = jdbcTemplate.query(USER_ITEMS, new Object[] { clientId }, new RowMapper<Equipment>() {
        	
			public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getEquipment(rs);
			}
		});

		return userItems;
	}
    
    @Override
    public void addRentedEquipment(String clientId, String equipmentId, String days) throws DAOException, EquipmentAlreadyExistsException {
    	try {
    		jdbcTemplate.update(ADD_RENTED_ITEM, clientId, equipmentId, new java.sql.Date(System.currentTimeMillis()), days);
    	} catch (DuplicateKeyException e) {
    		LOGGER.error(e.getMessage(), e);
    		throw new EquipmentAlreadyExistsException("duplicated primary key found. Item ID: " + equipmentId);
    	}
    }

	@Override
	@Cacheable(value = "items", key = "#id")
	public Equipment findCartEquipment(String id) throws DAOException {
		try {
			Equipment equipment = jdbcTemplate.queryForObject(FORM_CART_LIST, new Object[] { id }, new RowMapper<Equipment>() {

				@Override
				public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
					return getEquipment(rs);
				}
			});

			return equipment;
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in findCartEquipment", e);
		}
	}
    
	@Override
	public List<Equipment> formEquipmentList(String category) throws DAOException {
		final int ID = 1;
		final int TYPE = 2;
		final int NAME = 3;
		final int DESCRIPTION = 4;
		final int MANUFACTURER = 5;
		final int COST = 6;
		final int IMG = 7;
		final int CLIENT_ID = 8;
		
		List<Equipment> items = jdbcTemplate.query(FORM_EQUIPMENT_DATA, new Object[] { category }, new RowMapper<Equipment>() {

			@Override
			public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Equipment eq = new Equipment();
				
				eq.setId(rs.getString(ID));
				eq.setName(rs.getString(NAME));
				eq.setType(rs.getString(TYPE));
				eq.setDescription(rs.getString(DESCRIPTION));
				eq.setManufacturer(rs.getString(MANUFACTURER));
				eq.setPrice(rs.getInt(COST));
				eq.setImg(rs.getString(IMG));
				eq.setOwner(rs.getInt(CLIENT_ID));
				
				return eq;
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
	
	public Equipment getEquipment(ResultSet rs) throws SQLException {
    	final int ID = 1;
        final int TYPE = 2;
        final int NAME = 3;
        final int DESCRIPTION = 4;
        final int MANUFACTURER = 5;
        final int COST = 6;
        final int IMG = 7;
    	
        Equipment equipment = new Equipment();
        
  		equipment.setId(rs.getString(ID));
		equipment.setName(rs.getString(NAME));
		equipment.setType(rs.getString(TYPE));
		equipment.setDescription(rs.getString(DESCRIPTION));
		equipment.setManufacturer(rs.getString(MANUFACTURER));
		equipment.setPrice(rs.getInt(COST));
		equipment.setImg(rs.getString(IMG));
		
		return equipment;
    }
}
