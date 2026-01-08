package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.services.UserService;


public class UserDetailsAPITest {
	
	
	private UserService userService;
	
	@BeforeMethod(description = "Instanstiating the UserService object")
	public void setUp() {
		userService = new UserService();
	}

	@Test(description = "Verify if userdetails api response show correctly" , groups = {"smoke","reression","api"})
	public void userDetailsAPITest() {
		
		userService
			.userDetails(FD)
		.then()
			.spec(response_Spec_OK())
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
			.log().all();
	}
}
