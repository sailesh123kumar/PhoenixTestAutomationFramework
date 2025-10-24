package com.api.test;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayLoad;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import  static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;


public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Sailesh", "Kumar", "7823967575", "", "saileshkumar1793@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Rajiv Nagar", "Vignesh Salai", "Velachery", "Chennai", "600042", "India", "Tamil Nadu");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "81256049233069", "81256049233069", "81256049233069", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(1, "battery Issue");

		List<Problems> problemsList= new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayLoad payLoad = new CreateJobPayLoad(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);

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
	public void createJobAPIOneTest() {
		
		Customer customer = new Customer("Sailesh", "Kumar", "7823967575", "", "saileshkumar1793@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Rajiv Nagar", "Vignesh Salai", "Velachery", "Chennai", "600042", "India", "Tamil Nadu");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "11556049233069", "11556049233069", "11556049233069", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
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
