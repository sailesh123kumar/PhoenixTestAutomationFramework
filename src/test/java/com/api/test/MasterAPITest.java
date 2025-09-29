package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void verifyMasterAPITest() {
		
		//API Developed Incorrectly hen we are not passing any body , It supposed to be get request
		
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.header("Authorization",getToken(FD))
			.contentType("")  //default content-type application/url-form-encoded
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1500l))
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
	
	
	@Test
	public void invalidTokenMasterAPITest() {
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType("")  //default content-type application/url-form-encoded
		.log().uri()
		.log().method()
		.log().headers()
	.when()
		.post("master")
	.then()
		.log().all()
		.statusCode(401);
		
		
	}

}
