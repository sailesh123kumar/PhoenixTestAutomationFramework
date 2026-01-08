package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import static com.api.utils.SpecUtil.*;

import io.restassured.response.Response;

public class JobService {
	
	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	
	public Response create(Role role , Object payload) {
	return	given()
			.spec(request_SpecWithAuth(role, payload))
		   .when()
		   	.post(CREATE_JOB_ENDPOINT);
	}
	
	public Response search(Role role , Object payload) {
		return	given()
				.spec(request_SpecWithAuth(role, payload))
			   .when()
			   	.post(SEARCH_ENDPOINT);
		}

}
