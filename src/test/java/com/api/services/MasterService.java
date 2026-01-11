package com.api.services;

import static com.api.utils.SpecUtil.request_Spec;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.api.constants.Role;
import io.restassured.response.Response;

public class MasterService {

	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);
	private static final String MASTER_ENDPOINT = "/master";

	public Response master(Role role) {
		LOGGER.info("Making request to {} with the role {} ", MASTER_ENDPOINT, role);
		return given().spec(request_SpecWithAuth(role)).when().post(MASTER_ENDPOINT);
	}

	public Response masterWithNoAuth() {
		LOGGER.info("Making request to {} with no auth ", MASTER_ENDPOINT);
		return given().spec(request_Spec()).when().post(MASTER_ENDPOINT);
	}

}
