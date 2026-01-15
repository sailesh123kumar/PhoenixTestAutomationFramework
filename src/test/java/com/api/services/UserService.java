package com.api.services;

import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	private static final String USER_DETAILS_ENDPOINT = "/userdetails";

	
	
	@Step("Making UserDetails Api Request with role")
	public Response userDetails(Role role) {
		LOGGER.info("Making request to {} with the role {} ", USER_DETAILS_ENDPOINT, role);
		return given().spec(request_SpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
	}

}
