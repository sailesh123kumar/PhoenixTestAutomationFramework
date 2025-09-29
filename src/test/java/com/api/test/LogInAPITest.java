package com.api.test;

import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LogInAPITest {
	
	@Test
	public void loginAPITest() {
		
		UserCredentials userCredentials = new UserCredentials("iamfd","password");

		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.JSON)
			.body(userCredentials)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		.when()
			.post("login")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1500l))
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	}

}
