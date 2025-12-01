package com.api.test.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LogInAPIJSONDataDrivenTest {
	
	
	@Test(description = "Verify user is able to login successfully with the valid credentials" , groups = {"datadriven","reression","api"} , dataProviderClass = com.dataproviders.DataProvidersUtils.class , dataProvider = "LoginAPIJSONDataProvider")
	public void loginAPITest(UserCredentials  userCreadential) {
		
		given()
			.spec(request_Spec(userCreadential))
		.when()
			.post("login")
		.then()
			.spec(response_Spec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
