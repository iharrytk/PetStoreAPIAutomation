package PetStoreUserAPIAutomation;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class TestPetstoreUsingJsonFile extends RequestResponse {
	
	String username="Harry";
	String usernameupdated="Hermione";
	
	
	@Test(priority = 1,description = "TC001:Validate creating a user using POST call")
	public void createUserTest() {
		
		System.out.println("Begin Validating:TC001:Validate creating a user using POST call");
		
		ValidatableResponse resp=
		given()		
		.spec(req)
		.contentType("application/json")
		.body(new File("./src/test/resources/jsonfiles/postrequestuser.json"))
		.when()
		.post("/user")
		.then()
		.statusCode(200);
		
		System.out.println("The api response of the user which has been created is"+resp.extract().response().asPrettyString());
		
		
		//testng assertions
		Assert.assertEquals(resp.extract().statusCode(),200);
		Assert.assertEquals(resp.extract().jsonPath().getInt("code"),200);
		System.out.println("Endof Validating:TC001:Validate creating a user using POST call");
		
	}
	
	@Test(priority=2 ,description="TC002:Validate the created user is retrived using GET call")
	public void getUser() {
		System.out.println("Begin Validating:TC002:Validate the created user is retrived using GET call");
		
		ValidatableResponse resp=
				given()		
				.spec(req)
				.contentType("application/json")
				.pathParam("username", username)
				.when()
				.get("/user/{username}")
				.then()
				.statusCode(200);
				
				System.out.println("The api response of the user is"+resp.extract().response().asPrettyString());
				
				
				//testng assertions
				Assert.assertEquals(resp.extract().statusCode(),200);
				Assert.assertEquals(resp.extract().jsonPath().getString("username"),username);
		
		System.out.println("Endof Validating:TC002:Validate the created user is retrived using GET call");
	}
	
	@Test(priority=3 ,description="TC003:Validate the  user is updated using PUT call")
	public void updateUser() {
		System.out.println("Begin Validating:TC003:Validate the  user is updated using PUT call");
		
		ValidatableResponse resp=
				given()		
				.spec(req)
				.contentType("application/json")
				.pathParam("username", username)
				.body(new File("./src/test/resources/jsonfiles/putrequestuserupdate.json"))
				.when()
				.put("/user/{username}")
				.then()
				.statusCode(200);
				
				System.out.println("The api response of the user which has been updated is"+resp.extract().response().asPrettyString());
				
				
				//testng assertions
				Assert.assertEquals(resp.extract().statusCode(),200);
				
										
		System.out.println("Endof Validating:TC003:Validate the  user is updated using PUT call");	
					
	}
	
	@Test(priority=4 ,description="TC004:Validate the  user is deleted using DELETE call")
	public void deleteUser() {
		System.out.println("Begin Validating:TC004:Validate the  user is deleted using DELETE call");
		
		ValidatableResponse resp=
				given()		
				.spec(req)
				.contentType("application/json")
				.pathParam("username", usernameupdated)
				.when()
				.delete("/user/{username}")
				.then()
				.statusCode(200);
				
				System.out.println("The api response of the user is"+resp.extract().response().asPrettyString());
				
				
				//testng assertions
				Assert.assertEquals(resp.extract().statusCode(),200);
				
										
		System.out.println("Endof Validating:TC004:Validate the  user is deleted using DELETE call");		
					
	}
	
	
	

}
