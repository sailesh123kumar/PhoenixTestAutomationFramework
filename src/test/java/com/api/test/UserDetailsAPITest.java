package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.services.UserService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("User Manaagement")
@Feature("User Details")
@Listeners(com.listeners.APITestListener.class)
public class UserDetailsAPITest {
	
	
	private UserService userService;
	
	@BeforeMethod(description = "Instanstiating the UserService object")
	public void setUp() {
		userService = new UserService();
	}

	@Story("User Details should be shown")
	@Description("Verify if userdetails api response show correctly")
	@Severity(SeverityLevel.CRITICAL)
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
