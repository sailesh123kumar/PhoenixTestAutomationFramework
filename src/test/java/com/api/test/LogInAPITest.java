package com.api.test;

import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;


@Listeners(com.listeners.APITestListener.class)
public class LogInAPITest {
	
	private UserBean userBean;
	private AuthService authService;
	
	@BeforeMethod(description = "create request payload for login api and instanstiating the AuthService")
	public void setUp() {
		userBean = new UserBean("iamfd","password");
		authService = new AuthService();
	}
	
	@Test(description = "Verify user is able to login successfully with the valid credentials" , groups = {"smoke","reression","api"})
	public void loginAPITest() {
		 authService
		 	.login(userBean)
		.then()
			.spec(response_Spec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
