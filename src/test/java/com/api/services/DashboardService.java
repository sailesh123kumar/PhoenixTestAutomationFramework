package com.api.services;

import static com.api.utils.SpecUtil.request_Spec;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	public Response count(Role role) {
		
	return	given()
		.spec(request_SpecWithAuth(role))
	.when()
		.get(COUNT_ENDPOINT);
	}
	
	public Response countWithNoAuth() {
		return	given()
			.spec(request_Spec())
		.when()
			.get(COUNT_ENDPOINT);
		}

}
