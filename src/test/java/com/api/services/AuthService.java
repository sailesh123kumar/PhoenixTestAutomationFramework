package com.api.services;

import static com.api.utils.SpecUtil.request_Spec;
import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthService {

	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	private static final String LOGIN_ENDPOINT = "/login";

	@Step("Making the login request on the AuthService with the UserCredentials")
	public Response login(Object userCredentials) {
		LOGGER.info("Making login request for the payload {}", ((UserBean)userCredentials).getUsername());
		Response response = given()
				.spec(request_Spec(userCredentials))
				.when()
				.post(LOGIN_ENDPOINT);
		return response;
	}

}
