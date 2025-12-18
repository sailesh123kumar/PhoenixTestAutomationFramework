package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {

	// Executing the query for the tr_customer table! Which will get details of the
	// customer

	private static final String CUSTOMER_DETAIL_QUERY = """
			select * from tr_customer where id= ?
			""";
	
	private CustomerDao() {
	}

	public static CustomerDBModel getCustomerInfo(int customerId) {
		Connection connection = DatabaseManager.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		CustomerDBModel customerDBModel = null;
		try {
			preparedStatement = connection.prepareStatement(CUSTOMER_DETAIL_QUERY);
			preparedStatement.setInt(1, customerId);
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
			e.printStackTrace();
		}

		return customerDBModel;
	}

}
