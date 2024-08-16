package PetStorePetDetailsAPIAutomation;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class TestPetDetails extends RequestResponse {
	String payload="{\r\n"
			+ "  \"id\": 201,\r\n"
			+ "  \"category\": {\r\n"
			+ "    \"id\": 101,\r\n"
			+ "    \"name\": \"Golden Retriever\"\r\n"
			+ "  },\r\n"
			+ "  \"name\": \"Dogs\",\r\n"
			+ "  \"photoUrls\": [\r\n"
			+ "    \"https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg\"\r\n"
			+ "  ],\r\n"
			+ "  \"tags\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 101,\r\n"
			+ "      \"name\": \"Dogs\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"status\": \"available\"\r\n"
			+ "}";
	
	
	
	
	@Test(priority = 1,description = "TC001:Validate creating a pet using POST call")
	public void createPetTest() {
		
		System.out.println("Begin Validating:TC001:Validate creating a pet using POST call");
		
		ValidatableResponse resp=given().spec(req)
					.contentType("application/json")
						.when()
							.body(payload)
								.post("/pet")
									.then()
										.assertThat()
											.spec(response_200);
		
		String jsonResponse=resp.extract().asPrettyString();
		System.out.println("The JSON response is"+jsonResponse);
												
		
		//Testng assertions
		int code = resp.extract().statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
//		
//		
//		String id=resp.extract().jsonPath().getString("message");
//		System.out.println("Message id " +id);
//		Assert.assertEquals(id, "786");
		
		System.out.println("Endof Validating:TC001:Validate creating a pet using POST call");
		
	}

}
