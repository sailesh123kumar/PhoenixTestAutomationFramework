package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.request_Spec;
import static com.api.utils.SpecUtil.request_SpecWithAuth;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIPositiveTest(){
		given()
			.spec(request_SpecWithAuth(FD))
		.when()
			.get("dashboard/count")
		.then()
			.spec(response_Spec_OK())
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"))
			.body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		
	}
	
	
	@Test
	public void verifyMissingAuth_CountAPITest() {
		
		given()
		.spec(request_Spec())
	.when()
		.get("dashboard/count")
	.then()
		.spec(response_Spec_With_Text_StatusCode(401));
		
	}

}
