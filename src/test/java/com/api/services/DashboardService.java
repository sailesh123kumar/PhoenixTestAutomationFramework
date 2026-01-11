package com.api.services;

import static com.api.utils.SpecUtil.request_Spec;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.api.constants.Role;
import io.restassured.response.Response;

public class DashboardService {

	private static final Logger LOGGER = LogManager.getLogger(DashboardService.class);
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";

	public Response count(Role role) {
		LOGGER.info("Making request to the {} for the role {}", COUNT_ENDPOINT, role);
		return given().spec(request_SpecWithAuth(role)).when().get(COUNT_ENDPOINT);
	}

	public Response countWithNoAuth() {
		LOGGER.info("Making request to the {} with no auth", COUNT_ENDPOINT);
		return given().spec(request_Spec()).when().get(COUNT_ENDPOINT);
	}

	public Response details(Role role, Object payload) {
		LOGGER.info("Making request to the {} for the role {} and the payload {}", DETAIL_ENDPOINT, role, payload);
		return given().spec(request_SpecWithAuth(role, payload)).when().post(DETAIL_ENDPOINT);

	}

}
