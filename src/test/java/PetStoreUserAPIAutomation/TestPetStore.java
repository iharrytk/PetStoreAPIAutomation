package PetStoreUserAPIAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class TestPetStore extends RequestResponse{
	
	
	String payload="{\n"
			+ "  \"id\": 786,\n"
			+ "  \"username\": \"Haritha\",\n"
			+ "  \"firstName\": \"Haritha\",\n"
			+ "  \"lastName\": \"tk\",\n"
			+ "  \"email\": \"haritha@gmail.com\",\n"
			+ "  \"password\": \"mukesh@123\",\n"
			+ "  \"phone\": \"9012457896\",\n"
			+ "  \"userStatus\": 1\n"
			+ "}";
	
	String username="Haritha";
	String usernameupdated="Jaya Krishna";
	
	
	@Test(priority = 1,description = "TC001:Validate creating a user using POST call")
	public void createUserTest() {
		
		System.out.println("Begin Validating:TC001:Validate creating a user using POST call");
		
		ValidatableResponse resp=given().spec(req)
					.contentType("application/json")
						.when()
							.body(payload)
								.post("/user")
									.then()
										.assertThat()
											.spec(response_200);
		
		String jsonResponse=resp.extract().asPrettyString();
		System.out.println("The JSON response is"+jsonResponse);
												
		
		//Testng assertions
		int code = resp.extract().statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
		
		
		String id=resp.extract().jsonPath().getString("message");
		System.out.println("Message id " +id);
		Assert.assertEquals(id, "786");
		
		System.out.println("Endof Validating:TC001:Validate creating a user using POST call");
		
	}
	
	@Test(priority=2 ,description="TC002:Validate the created user is retrived using GET call")
	public void getUser() {
		System.out.println("Begin Validating:TC002:Validate the created user is retrived using GET call");
		
		ValidatableResponse resp=given()
			.spec(req)
				.pathParam("username", username)
					.when()
						.get("/user/{username}")
							.then()
								.statusCode(200)
									.body("id", not(empty()))//Hamcrest assertions
										.body("username", not(empty()));//Hamcrest assertions
		
		//Testng assertions
		int code = resp.extract().statusCode();
		System.out.println("status code " +code);
		Assert.assertEquals(code, 200);
								
		String usernameactual=resp.extract().response().jsonPath().getString("username");
		Assert.assertEquals(usernameactual, username);
		
		System.out.println("Endof Validating:TC002:Validate the created user is retrived using GET call");
	}
	
	@Test(priority=3 ,description="TC003:Validate the  user is updated using PUT call")
	public void updateUser() {
		System.out.println("Begin Validating:TC003:Validate the  user is updated using PUT call");
		
		ValidatableResponse resp=
				given()
				.spec(req)
					.headers("Accept", "application/json")
						.headers("Content-Type", "application/json")
							.pathParam("username", username)
								.when()
									.body("{\n"
											+ "\"id\": 786,\n"
											+ "\"username\": \""+usernameupdated+"\",\n"
											+ "\"firstName\": \"Haritha\",\n"
											+ "\"lastName\": \"tk\",\n"
											+ "\"email\": \"haritha@gmail.com\",\n"
											+ "\"password\": \"mukesh@123\",\n"
											+ "\"phone\": \"9012457896\",\n"
											+ "\"userStatus\": 1\n"
											+ "}")
										.put("/user/{username}")
											.then()
											.statusCode(200);
										
										
		
				//Testng assertions
				int code = resp.extract().statusCode();
				System.out.println("status code " +code);
				Assert.assertEquals(code, 200);
										
				System.out.println("Endof Validating:TC003:Validate the  user is updated using PUT call");	
					
	}
	
	@Test(priority=4 ,description="TC004:Validate the  user is deleted using DELETE call")
	public void deleteUser() {
		System.out.println("Begin Validating:TC004:Validate the  user is deleted using DELETE call");
		
		ValidatableResponse resp=
				given()
				.spec(req)
					.headers("Accept", "application/json")
							.pathParam("username", usernameupdated)
								.when()
									.delete("/user/{username}")
										.then()
											.statusCode(200);
										
										
		
				//Testng assertions
				int code = resp.extract().statusCode();
				System.out.println("status code " +code);
				Assert.assertEquals(code, 200);
										
				System.out.println("Endof Validating:TC004:Validate the  user is deleted using DELETE call");		
					
	}
	
	
	
	
	
	
	

}
