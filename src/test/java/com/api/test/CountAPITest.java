package com.api.test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIPositiveTest(){
		given()
			.baseUri(getProperty("BASE_URI"))
			.header("Authorization",getToken(FD))
			.log().uri()
			.log().method()
		.when()
			.get("dashboard/count")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1500l))
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"))
			.body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		
	}
	
	
	@Test
	public void verifyMissingAuth_CountAPITest() {
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.log().uri()
		.log().method()
		.log().method()
	.when()
		.get("dashboard/count")
	.then()
		.log().all()
		.statusCode(401);
		
	}

}
