package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.Product;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.FakerDataGenerator;

import io.restassured.http.ContentType;

public class CreateJobAPIWithFakeDataTest {


	@Test(description = "Verify Create job Api is able to create Inwarranty job", groups = { "api" ,"smoke", "regression" },dataProviderClass = com.dataproviders.DataProvidersUtils.class , dataProvider = "createJobAPIFakerDataProvider" )
	public void createJobAPITest(CreateJobPayload payLoad) {

		given().baseUri(getProperty("BASE_URI")).header("Authorization", getToken(FD)).contentType(ContentType.JSON)
				.body(payLoad).log().all().when().post("job/create").then().log().all().statusCode(200)
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));
	}

	
}
