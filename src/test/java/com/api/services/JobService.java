package com.api.services;

import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import static com.api.utils.SpecUtil.*;
import io.restassured.response.Response;

public class JobService {

	private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	

	public Response create(Role role, CreateJobPayload payload) {
		LOGGER.info("Making request to {} with the role {} and the payload {}",CREATE_JOB_ENDPOINT,role, payload);
		return given().spec(request_SpecWithAuth(role, payload)).when().post(CREATE_JOB_ENDPOINT);
	}

	public Response search(Role role, Object payload) {
		LOGGER.info("Making request to {} with the role {} and the payload {}",SEARCH_ENDPOINT,role, payload);
		return given().spec(request_SpecWithAuth(role, payload)).when().post(SEARCH_ENDPOINT);
	}

}
