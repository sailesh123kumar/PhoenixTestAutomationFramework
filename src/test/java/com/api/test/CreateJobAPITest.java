package com.api.test;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayLoad;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;


public class CreateJobAPITest {
	
	@Test
	public void createjobAPITest() {
		
		Customer customer = new Customer("Sailesh", "Kumar", "7823967575", "", "saileshkumar1793@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Rajiv Nagar", "Vignesh Salai", "Velachery", "Chennai", "600042", "India", "Tamil Nadu");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "11256049233069", "11256049233069", "11056049233069", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "battery Issue");

		List<Problems> problemsList= new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayLoad payLoad = new CreateJobPayLoad(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);

		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.header("Authorization",AuthTokenProvider.getToken(Role.FD))
			.contentType(ContentType.JSON)
			.body(payLoad)
			.log().all()
		.when()
			.post("job/create")
		.then()
			.log().all()
			.statusCode(200)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			.body("message", equalTo("Job created successfully. "))
			.body("data.mst_service_location_id", equalTo(1))
			.body("data.job_number", startsWith("JOB_"));
	}
	
	@Test
	public void createjobAPIOneTest() {
		
		Customer customer = new Customer("Sailesh", "Kumar", "7823967575", "", "saileshkumar1793@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Rajiv Nagar", "Vignesh Salai", "Velachery", "Chennai", "600042", "India", "Tamil Nadu");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "11556049233069", "11556049233069", "11556049233069", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "battery Issue");
		List<Problems> problemsList= new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayLoad payLoad = new CreateJobPayLoad(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);

		given()
			.spec(SpecUtil.request_SpecWithAuth(Role.FD,payLoad))
		.when()
			.post("job/create")
		.then()
			.spec(SpecUtil.response_Spec_OK());
	}
	

}
