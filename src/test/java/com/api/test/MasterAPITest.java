package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.request_Spec;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static com.api.utils.SpecUtil.response_Spec_With_Text_StatusCode;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

public class MasterAPITest {
	
	@Test(description = "Verify Master Api is giving correct response", groups = {"smoke" , "regression"})
	public void verifyMasterAPITest() {
		
		//API Developed Incorrectly hen we are not passing any body , It supposed to be get request
		
		given()
			.spec(request_SpecWithAuth(FD))
		.when()
			.post("master")
		.then()
			.spec(response_Spec_OK())
			.body("$", hasKey("message"))
			.body("$", hasKey("data"))
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body("data", hasKey("mst_oem"))
			.body("data", hasKey("mst_model"))
			.body("data.mst_oem.size()", equalTo(2))  //To check size of Json Array
			.body("data.mst_model.size()", greaterThan(0))
			.body("data.mst_oem.id", everyItem(notNullValue()))
			.body("data.mst_oem.name", everyItem(notNullValue()))
			.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
			
	}
	
	
	@Test(description = "Verify Master Api is giving correct status code for invalid token", groups = {"smoke" , "negative" , "regression"})
	public void invalidTokenMasterAPITest() {
		
		given()
		.spec(request_Spec())
	.when()
		.post("master")
	.then()
		.spec(response_Spec_With_Text_StatusCode(401));
		
		
	}

}
