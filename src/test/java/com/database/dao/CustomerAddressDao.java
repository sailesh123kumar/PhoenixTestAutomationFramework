package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

import io.qameta.allure.Step;

public class CustomerAddressDao {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerAddressDao.class);

	private static final String CUSTOMER_ADDRESS_DETAIL_QUERY = """

			SELECT
					id,
					flat_number,
					apartment_name,
					street_name,
					landmark,
					area,
					pincode,
					country,
					state

			from tr_customer_address
			where id=?

			""";
	
	
	private CustomerAddressDao() {

	}

	@Step("Retrieving the Customer Address payload data from Database for the specific customerAddressId")
	public static CustomerAddressDBModel getCustomerAddress(int customerAddressId) {
		Connection connection = DatabaseManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		CustomerAddressDBModel customerAddressDBModel = null;
		try {
			LOGGER.info("Getting the connection from DBManager");
			statement = connection.prepareStatement(CUSTOMER_ADDRESS_DETAIL_QUERY);
			statement.setInt(1, customerAddressId);
			
			LOGGER.info("Executing the SQL Query {} ", CUSTOMER_ADDRESS_DETAIL_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				customerAddressDBModel = new CustomerAddressDBModel(resultSet.getInt("id"),
						resultSet.getString("flat_number"), resultSet.getString("apartment_name"),
						resultSet.getString("street_name"), resultSet.getString("landmark"),
						resultSet.getString("area"), resultSet.getString("pincode"), resultSet.getString("country"),
						resultSet.getString("state"));

			}
		} catch (SQLException e) {
			LOGGER.error("Cannot convert the resultset to CustomerAddressDBModel bean", e);
			e.printStackTrace();
		}
		return customerAddressDBModel;
	}

}
