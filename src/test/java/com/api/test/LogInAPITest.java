package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LogInAPITest {
	
	@Test
	public void loginAPITest() {
		
		UserCredentials userCredentials = new UserCredentials("iamfd","password");

		given()
			.spec(request_Spec(userCredentials))
		.when()
			.post("login")
		.then()
			.spec(response_Spec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
