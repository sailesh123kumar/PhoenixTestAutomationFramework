package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("*********************** REQUEST DETAILS ***********************");
		LOGGER.info("BASE URI : {} ",requestSpec.getURI());
		LOGGER.info("REQUEST METHOD : {} ",requestSpec.getMethod());
		redactRequestHeader(requestSpec);
		redactRequestPayload(requestSpec);
		
		Response response = ctx.next(requestSpec, responseSpec);
		LOGGER.info("*********************** RESPONSE DETAILS ***********************");
		LOGGER.info("STATUS LINE : {} ",response.statusLine());
		LOGGER.info("RESPONSE TIME : {}/ms",response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("RESPONSE HEADERS : \n {} ",response.getHeaders());
		redactResponseBody(response);
		
		return response;
	}
	
	
	private void redactRequestHeader(FilterableRequestSpecification requestSpec) {
		List<Header> headerList = requestSpec.getHeaders().asList();
		LOGGER.info("=================== HEADER DETAILS ===================");
		for (Header current : headerList) {
			if(current.getName().equalsIgnoreCase("Authorization")) {
				LOGGER.info("{} : {} ",current.getName() , "\"[REDACTED]\"");
			}
			else {
				LOGGER.info("{} : {} " ,current.getName() , current.getValue());
			}
		}
	}
	
	
	private void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"","\"token\": \"[REDACTED]\"");
		LOGGER.info("RESPONSE BODY : \n {}" , 		responseBody);
	}


	public void redactRequestPayload(FilterableRequestSpecification requestSpec) {
		if(requestSpec.getBody()!=null) {
			//Only for POST PUT and DEL payload
			String requestPayload = requestSpec.getBody().toString();
			requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"","\"password\": \"[REDACTED]\"");
			LOGGER.info("REQUEST PAYLOAD : \n {}" , requestPayload);
		}
	}

}
