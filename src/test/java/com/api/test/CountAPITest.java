package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.response_Spec_OK;
import static com.api.utils.SpecUtil.response_Spec_With_Text_StatusCode;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.services.DashboardService;

public class CountAPITest {
	
	private DashboardService dashBoardService;
	
	@BeforeMethod(description = "Instantiating the Dashboard Service object reference")
	public void setup() {
		dashBoardService = new DashboardService();
	}
	
	@Test(description = "Verify Count Api is giving correct response", groups = {"smoke" , "regression"})
	public void verifyCountAPIPositiveTest(){
		
		dashBoardService
			.count(FD)
		.then()
			.spec(response_Spec_OK())
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"))
			.body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		
	}
	
	
	@Test(description = "Verify Count Api is giving correct status code for invalid token", groups = {"smoke" , "negative" , "regression"})
	public void verifyMissingAuth_CountAPITest() {
		
		dashBoardService
			.countWithNoAuth()
		.then()
			.spec(response_Spec_With_Text_StatusCode(401));
		
	}

}
