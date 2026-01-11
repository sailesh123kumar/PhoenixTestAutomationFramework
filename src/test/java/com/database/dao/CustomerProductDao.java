package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerProductDao.class);

	private static final String PRODUCT_QUERY = """

			SELECT * from tr_customer_product where id = ?;

			""";
	
	
	private  CustomerProductDao() {
	}

	public static CustomerProductDBModel getCustomerProductFromDB(int tr_customer_product_id) {

		Connection connection = DatabaseManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		CustomerProductDBModel customerProductDBModel = null;

		try {
			LOGGER.info("Getting the connection from DBManager");
			statement = connection.prepareStatement(PRODUCT_QUERY);
			statement.setInt(1, tr_customer_product_id);
			LOGGER.info("Executing the SQL Query {} ", PRODUCT_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("id"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("mst_model_id"),
						resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"),
						resultSet.getString("imei1"), resultSet.getString("serial_number"));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot convert the resultset to CustomerProductDBModel bean", e);
			e.printStackTrace();
		}

		return customerProductDBModel;
	}

}
