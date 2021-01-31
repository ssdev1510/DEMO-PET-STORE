package payloadData;
/**
 * Created by sahil singla on 30/01/2021.
 */
public class PetPayload {
	
//Pet payload
public String petRequestBody(int random, String status) {
	
	String body = "{\r\n"
			+ "  \"id\": "+random+",\r\n"
			+ "  \"category\": {\r\n"
			+ "    \"id\": 0,\r\n"
			+ "    \"name\": \"animal\"\r\n"
			+ "  },\r\n"
			+ "  \"name\": \"cat\",\r\n"
			+ "  \"photoUrls\": [\r\n"
			+ "    \"string\"\r\n"
			+ "  ],\r\n"
			+ "  \"tags\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 0,\r\n"
			+ "      \"name\": \"cat\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"status\": \""+status+"\"\r\n"
			+ "}";
	return body;
}

}
