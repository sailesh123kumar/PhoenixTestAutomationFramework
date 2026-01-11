package com.api.test.datadriven;

import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

public class LogInAPIJSONDataDrivenTest {
	
private AuthService authService;
	
	@BeforeMethod(description = "Instanstiating the AuthService object")
	public void setUp() {
		authService = new AuthService();
	}
	
	
	
	@Test(description = "Verify user is able to login successfully with the valid credentials" , groups = {"datadriven","reression","api"} , dataProviderClass = com.dataproviders.DataProvidersUtils.class , dataProvider = "LoginAPIJSONDataProvider")
	public void loginAPITest(UserBean  userBean) {
		
		authService
			.login(userBean)
		.then()
			.spec(response_Spec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
