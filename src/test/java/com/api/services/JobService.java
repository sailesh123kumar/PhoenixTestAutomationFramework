package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import static com.api.utils.SpecUtil.*;

import io.restassured.response.Response;

public class JobService {
	
	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	
	public Response create(Role role , Object payload) {
	return	given()
			.spec(request_SpecWithAuth(role, payload))
		   .when()
		   	.post(CREATE_JOB_ENDPOINT);
	}

}
