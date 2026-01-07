package com.api.test;

import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.request.model.UserCredentials;
import com.api.services.AuthService;

public class LogInAPITest {
	
	private UserCredentials userCredentials;
	private AuthService authService;
	
	@BeforeMethod(description = "create request payload for login api and instanstiating the AuthService")
	public void setUp() {
		userCredentials = new UserCredentials("iamfd","password");
		authService = new AuthService();
	}
	
	@Test(description = "Verify user is able to login successfully with the valid credentials" , groups = {"smoke","reression","api"})
	public void loginAPITest() {
		 authService
		 	.login(userCredentials)
		.then()
			.spec(response_Spec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
