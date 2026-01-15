package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemDBModel;

import io.qameta.allure.Step;

public class MapJobProblemDao {
	
	private static final Logger LOGGER = LogManager.getLogger(MapJobProblemDao.class);

	private static final String MAP_JOB_PROBLEM_QUERY = """
			select * from map_job_problem  where tr_job_head_id  = ?
			""";

	private MapJobProblemDao() {
	}

	
	@Step("Retrieving the MapJobProblem payload data from Database for the specific tr_job_head_id")
	public static MapJobProblemDBModel getProblemDataFromDB(int tr_job_head_id) {

		Connection connection = DatabaseManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		MapJobProblemDBModel mapJobProblemDBModel = null;
		try {
			
			LOGGER.info("Getting the connection from DBManager");
			statement = connection.prepareStatement(MAP_JOB_PROBLEM_QUERY);
			statement.setInt(1, tr_job_head_id);
			LOGGER.info("Executing the SQL Query {} ", MAP_JOB_PROBLEM_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				mapJobProblemDBModel = new MapJobProblemDBModel(resultSet.getInt("id"), 
						resultSet.getInt("tr_job_head_id"), resultSet.getInt("mst_problem_id"), resultSet.getString("remark"));
			}

		} catch (SQLException e) {
			LOGGER.error("Cannot convert the resultset to MapJobProblemDBModel bean", e);
			e.printStackTrace();
		}

		return mapJobProblemDBModel;
	}

}
