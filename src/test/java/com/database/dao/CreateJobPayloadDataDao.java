package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DatabaseManager;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobPayloadDataDao {

	private static final String SQL_QUERY = """

				SELECT
				first_name,
				last_name,
				mobile_number,
				mobile_number_alt,
				email_id,
				email_id_alt,
				flat_number,
				apartment_name,
				street_name,
				landmark,
				area,
				pincode,
				country,
				state,
				mst_model_id,
				dop,
				popurl,
				imei2,
				imei1,
				serial_number ,
				mst_service_location_id,
				mst_platform_id,
				mst_warrenty_status_id,
				mst_oem_id,
				mst_problem_id,
				remark
			FROM
				tr_customer
			inner join tr_customer_address
			on
				tr_customer.tr_customer_address_id = tr_customer_address.id
			inner join tr_customer_product
			on
				tr_customer_product.tr_customer_id = tr_customer.id
			inner join tr_job_head
				on
				tr_job_head.tr_customer_id = tr_customer.id
			inner join map_job_problem
				on
				tr_job_head.id = map_job_problem.tr_job_head_id
			limit 5;

						""";
	
	
	private CreateJobPayloadDataDao() {
		
	}

	public static List<CreateJobBean> getCreateJobDBData() {

		Connection connection = DatabaseManager.getConnection();
		CreateJobBean createJobBean;
		Statement statement;
		ResultSet resultSet;
		List<CreateJobBean> beanList = new ArrayList<CreateJobBean>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY);

			while (resultSet.next()) {

				createJobBean = new CreateJobBean();

				createJobBean.setCustomer__first_name(resultSet.getString("first_name"));
				createJobBean.setCustomer__last_name(resultSet.getString("last_name"));
				createJobBean.setCustomer__mobile_number(resultSet.getString("mobile_number"));
				createJobBean.setCustomer__mobile_number_alt(resultSet.getString("mobile_number_alt"));
				createJobBean.setCustomer__email_id(resultSet.getString("email_id"));
				createJobBean.setCustomer__email_id_alt(resultSet.getString("email_id_alt"));
				createJobBean.setCustomer_address__flat_number(resultSet.getString("flat_number"));
				createJobBean.setCustomer_address__apartment_name(resultSet.getString("apartment_name"));
				createJobBean.setCustomer_address__street_name(resultSet.getString("street_name"));
				createJobBean.setCustomer_address__landmark(resultSet.getString("landmark"));
				createJobBean.setCustomer_address__area(resultSet.getString("area"));
				createJobBean.setCustomer_address__pincode(resultSet.getString("pincode"));
				createJobBean.setCustomer_address__country(resultSet.getString("country"));
				createJobBean.setCustomer_address__state(resultSet.getString("state"));
				createJobBean.setCustomer_product__dop(resultSet.getString("dop"));
				createJobBean.setCustomer_product__popurl(resultSet.getString("popurl"));
				createJobBean.setCustomer_product__imei2(resultSet.getString("imei2"));
				createJobBean.setCustomer_product__imei1(resultSet.getString("imei1"));
				createJobBean.setCustomer_product__serial_number(resultSet.getString("serial_number"));
				createJobBean.setMst_service_location_id(resultSet.getString("mst_service_location_id"));
				createJobBean.setMst_platform_id(resultSet.getString("mst_platform_id"));
				createJobBean.setMst_warrenty_status_id(resultSet.getString("mst_warrenty_status_id"));
				createJobBean.setProblems__id(resultSet.getString("mst_problem_id"));
				createJobBean.setProblems__remark(resultSet.getString("remark"));

				createJobBean.setCustomer_product__product_id("1"); // This as 1
				createJobBean.setCustomer_product__mst_model_id(resultSet.getString("mst_model_id")); // This as 1
				createJobBean.setMst_oem_id(resultSet.getString("mst_oem_id")); // This as 1

				beanList.add(createJobBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return beanList;
	}

}
