package com.api.utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	
	public static String getToken(Role role) {
		
		UserCredentials userCredentials = null;
		
		if(role == FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		}
		else if(role == SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		}
		else if(role == ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		}
		else if(role == QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}
		
		String token = given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userCredentials)
		.when()
			.post("login")
			.then()
			.log()
			.ifValidationFails()
			.statusCode(200)
			.body("message", equalTo("Success"))
			.extract()
			.jsonPath()
			.getString("data.token");
		
		return token;
		
	}

}
