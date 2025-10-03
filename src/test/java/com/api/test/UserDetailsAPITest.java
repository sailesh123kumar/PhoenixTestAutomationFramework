package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;


public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() {
		given()
			.spec(request_SpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(response_Spec_OK())
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
			.log().all();
	}
}
