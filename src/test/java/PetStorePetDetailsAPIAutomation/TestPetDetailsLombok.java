package PetStorePetDetailsAPIAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import PetStorePetDetailsAPIAutomation.LombokPOJOPostPet.Category;
import PetStorePetDetailsAPIAutomation.LombokPOJOPostPet.Tags;
import io.restassured.response.ValidatableResponse;

public class TestPetDetailsLombok extends RequestResponse {
	Category category=new Category(101,"Golden Retriever");
	Tags tags=new Tags(101,"Dogs");
	List<String> ph = new ArrayList<String>();
	
	LombokPOJOPostPet payload=new LombokPOJOPostPet(201,category,"Dogs",Collections.singletonList("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"),Collections.singletonList(tags),"available");
	
	

	
	@Test(priority = 1,description = "TC001:Validate creating a pet using POST call Lombok without builder pattern")
	public void createPetTest() {
		
		System.out.println("Begin Validating:TC001:Validate creating a pet using POST call Lombok without builder pattern");
		
		ValidatableResponse resp=given().spec(req)
					.contentType("application/json")
						.when()
							.body(payload)
								.post("/pet")
									.then()
									.assertThat()
									.spec(response_200)
											.body("id", equalTo(201))
							                .body("category.id", equalTo(101))
							                .body("category.name", equalTo("Golden Retriever"))
							                .body("name", equalTo("Dogs"))
							                .body("photoUrls[0]", equalTo("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"))
							                .body("tags[0].id", equalTo(101))
							                .body("tags[0].name", equalTo("Dogs"))
							                .body("status", equalTo("available"));

		
		String jsonResponse=resp.extract().asPrettyString();
		System.out.println("The JSON response is"+jsonResponse);
											
		
		System.out.println("Endof Validating:TC001:Validate creating a pet using POST call Lombok without builder pattern");
		
	}
}


/*JSON Payload for which the Lombok POJO has been developed:
{
	  "id": 201,
	  "category": {
	    "id": 101,
	    "name": "Golden Retriever"
	  },
	  "name": "Dogs",
	  "photoUrls": [
	    "https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"
	  ],
	  "tags": [
	    {
	      "id": 101,
	      "name": "Dogs"
	    }
	  ],
	  "status": "available"
	}
	
	*/
