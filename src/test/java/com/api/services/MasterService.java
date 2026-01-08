package com.api.services;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.request_Spec;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT = "/master";
	
	
	public Response master(Role role) {
		return given()
				.spec(request_SpecWithAuth(role))
			  .when()
			   	.post(MASTER_ENDPOINT);
	}
	
	public Response masterWithNoAuth() {
		return given()
				.spec(request_Spec())
			  .when()
			   	.post(MASTER_ENDPOINT);
	}

}
