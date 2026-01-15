package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

import io.qameta.allure.Step;

public class CustomerDao {

	// Executing the query for the tr_customer table! Which will get details of the
	// customer
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);

	private static final String CUSTOMER_DETAIL_QUERY = """
			select * from tr_customer where id= ?
			""";
	
	private CustomerDao() {
	}

	@Step("Retrieving the Customer Info payload data from Database for the specific customerId")
	public static CustomerDBModel getCustomerInfo(int customerId) {
		Connection connection = DatabaseManager.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		CustomerDBModel customerDBModel = null;
		try {
			LOGGER.info("Getting the connection from DBManager");
			preparedStatement = connection.prepareStatement(CUSTOMER_DETAIL_QUERY);
			preparedStatement.setInt(1, customerId);
			
			LOGGER.info("Executing the SQL Query {} ", CUSTOMER_DETAIL_QUERY);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customerDBModel = new CustomerDBModel();
				customerDBModel.setId(resultSet.getInt("id"));
				customerDBModel.setFirst_name(resultSet.getString("first_name"));
				customerDBModel.setLast_name(resultSet.getString("last_name"));
				customerDBModel.setMobile_number(resultSet.getString("mobile_number"));
				customerDBModel.setMobile_number_alt(resultSet.getString("mobile_number_alt"));
				customerDBModel.setEmail_id(resultSet.getString("email_id"));
				customerDBModel.setEmail_id_alt(resultSet.getString("email_id_alt"));
				customerDBModel.setTr_customer_address_id(resultSet.getInt("tr_customer_address_id"));

			}
		} catch (SQLException e) {
			LOGGER.error("Cannot convert the resultset to CustomerDBModel bean", e);
			e.printStackTrace();
		}

		return customerDBModel;
	}

}
