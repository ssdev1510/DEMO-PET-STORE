package controllers;

/**
 * Created by sahil singla on 30/01/2021.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloadData.PetPayload;
import utils.JavaUtils;

public class PetStoreController {

	JavaUtils util = new JavaUtils();

	public static String BASE_URL;
	private static RequestSpecification requestSpecification;
	private static Response response;
	private final int random = util.randonNumber();
	private static int newAddedPetId;
	private static Logger Log = LogManager.getLogger();
	private int result;

//Initialization
	public void petStoreInitialization(String baseURL) {
		RestAssured.baseURI = baseURL;
		requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
	}

//Getting Pet details	
	public void getRequestPetController(String status1, String endPoint) {
		response = requestSpecification.queryParam("status", status1).get(endPoint).then().log().all().extract()
				.response();
		Log.info("Getting Pets details available in Store- Status code : " + response.getStatusCode());
	}

//Verifying All available Pet details	
	public int verifyGetResponse() {
		result = response.getStatusCode();
		Log.info("Success - Pets details avaiable in Store in JSON Format: " + response.prettyPrint()
				+ " with status code : " + response.getStatusCode());
		return result;
	}

//Adding new pet
	public void postRequestPetController(String status) {
		PetPayload payPet = new PetPayload();
		response = requestSpecification.body(payPet.petRequestBody(random, status)).post().then().log().all().extract()
				.response();
		Log.info("Adding the new pet with ID : " + random + " with status code : " + response.getStatusCode());
	}

//Verify new added Pet
	public int verifyAddedPet() {
		response = requestSpecification.pathParam("petId", random).when().get("/{petId}").then().log().all().extract()
				.response();
		result = response.getStatusCode();
		Log.info("Success- Newly added pet details in JSON Format :  " + response.prettyPrint());
		return result;
	}

//Updating pet
	public void putRequestPetController(String status) {
		System.out.println("verify put");
		PetPayload uppayPet = new PetPayload();
		// response = requestSpecification.body(uppayPet.petRequestBody(random,
		// status)).put().then().log().all().extract().response();
		response = RestAssured.given().contentType(ContentType.JSON).body(uppayPet.petRequestBody(random, status)).put()
				.then().log().all().extract().response();
		Log.info("Updating the newly added pet from AVAILABLE to SOLD status with status code : "
				+ response.getStatusCode());
	}

//Verify pet update with status sold	
	public String verifyUpdatedPet() {
		response = requestSpecification.pathParam("petId", random).when().get("/{petId}").then().log().all().extract()
				.response();
		String newStatus = response.getBody().jsonPath().getString("status");
		Log.info("Success - current status of newly added pet is  :  " + newStatus + " and Updated JSON format :  "
				+ response.prettyPrint());
		return newStatus;

	}

//Deleting the created pet	 
	public void deleteRequestPetController() {
		response = requestSpecification.pathParam("petId", random).delete("/{petId}").then().log().all().extract()
				.response();
		Log.info("Deleting the newly added pet : " + response.getStatusCode());
	}

//Verify the delete pet
	public boolean verifyPetDeleted() {
		boolean result = false;
		response = requestSpecification.pathParam("petId", newAddedPetId).when().get("/{petId}").then().log().all()
				.extract().response();
		if (response.getStatusCode() == 404) {
			Log.info("Success - Deleted the pet with success status code : " + response.getStatusCode());
			result = true;
		} else {
			Log.info("Failed - Deletion of pet unsuccess with status code : " + response.getStatusCode());
		}
		return result;
	}

}
