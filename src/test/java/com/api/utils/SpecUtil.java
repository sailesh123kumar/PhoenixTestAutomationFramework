package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	
	// GET - DELETE
	
	public static RequestSpecification request_Spec() {
		
		RequestSpecification requestSpecification =  new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addFilter(new SensitiveDataFilter())
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	
	public static RequestSpecification request_Spec(Object requestPayload) {
		
		RequestSpecification requestSpecification =  new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(requestPayload)
		.addFilter(new SensitiveDataFilter())
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	public static RequestSpecification request_SpecWithAuth(Role role) {
		
		RequestSpecification requestSpecification =  new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
				
				return requestSpecification;
	}
	
public static RequestSpecification request_SpecWithAuth(Role role , Object payload) {
		
		RequestSpecification requestSpecification =  new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
				
				return requestSpecification;
	}
	
	
	
	public static ResponseSpecification response_Spec_OK() {
	
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(2500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification response_Spec_WithStatusCode(int statusCode) {
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(2500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
public static ResponseSpecification response_Spec_With_Text_StatusCode(int statusCode) {
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(2500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	
	

}
