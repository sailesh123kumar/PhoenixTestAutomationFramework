package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.JobHeadDBModel;

public class JobHeadDao {
	
	private static final Logger LOGGER = LogManager.getLogger(JobHeadDao.class);

	private static final String JOB_HEAD_QUERY = """
			select * from tr_job_head where tr_customer_id = ?
			""";
	
	private JobHeadDao() {
		
	}

	public static JobHeadDBModel getJobHeadinfoFromDB(int tr_customer_id) {

		Connection connection = DatabaseManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		JobHeadDBModel jobHeadModel = null;
		try {
			LOGGER.info("Getting the connection from DBManager");
			statement = connection.prepareStatement(JOB_HEAD_QUERY);
			statement.setInt(1, tr_customer_id);
			LOGGER.info("Executing the SQL Query {} ", JOB_HEAD_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				jobHeadModel = new JobHeadDBModel(resultSet.getInt("id"), resultSet.getString("job_number"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("tr_customer_product_id"),
						resultSet.getInt("mst_service_location_id"), resultSet.getInt("mst_platform_id"),
						resultSet.getInt("mst_warrenty_status_id"), resultSet.getInt("mst_oem_id"));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot convert the resultset to JobHeadDBModel bean", e);
			e.printStackTrace();
		}
		
		return jobHeadModel;
	}

}
