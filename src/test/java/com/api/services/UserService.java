package com.api.services;

import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static io.restassured.RestAssured.given;
import com.api.constants.Role;

import io.restassured.response.Response;

public class UserService {
	
	private static final String USER_DETAILS_ENDPOINT ="/userdetails";
	
	public Response userDetails(Role role) {
		
		return given()
		.spec(request_SpecWithAuth(role))
		.when()
		.get(USER_DETAILS_ENDPOINT);
		
	}

}
