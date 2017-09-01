package by.shop.rent.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import by.shop.rent.dao.interfaces.InfoDAO;
import by.shop.rent.dao.objects.Info;

@Component("mysqlDao")
public class MySQLDao implements InfoDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDtaSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void getInfo(Info info) {
		
		List<Info> infoList;
		String sql = "SELECT one, two FROM test";
		infoList = jdbcTemplate.query(sql, new RowMapper<Info>() {
			
			@Override
			public Info mapRow(ResultSet rs, int rowNum) throws SQLException {
				Info info = new Info();
				//info.setOne(rs.getString(1));
				//info.setTwo(rs.getString(2));
				
				System.out.println(rs.getString(1)+" "+rowNum);
				System.out.println(rs.getString(2)+" "+rowNum);
				
				return info;
			}
		});
	}

}
