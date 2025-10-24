package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LogInAPITest {
	
	private UserCredentials userCredentials;
	
	@BeforeMethod(description = "create request payload for login api")
	public void setUp() {
		userCredentials = new UserCredentials("iamfd","password");
	}
	
	@Test(description = "Verify user is able to login successfully with the valid credentials" , groups = {"smoke","reression","api"})
	public void loginAPITest() {
		
		given()
			.spec(request_Spec(userCredentials))
		.when()
			.post("login")
		.then()
			.spec(response_Spec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
