package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {

	private static final String PRODUCT_QUERY = """

			SELECT * from tr_customer_product where id = ?;

			""";

	public static CustomerProductDBModel getCustomerProductFromDB(int tr_customer_product_id) {

		Connection connection = DatabaseManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		CustomerProductDBModel customerProductDBModel = null;

		try {
			statement = connection.prepareStatement(PRODUCT_QUERY);
			statement.setInt(1, tr_customer_product_id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("id"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("mst_model_id"),
						resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"),
						resultSet.getString("imei1"), resultSet.getString("serial_number"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerProductDBModel;
	}

}
