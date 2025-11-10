package com.petstore.utils;

import com.petstore.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ApiUtils is a utility class that provides reusable methods for performing
 * CRUD operations against the PetStore API using RestAssured.
 *
 * <p>This class centralizes API calls (POST, GET, PUT, DELETE) and
 * provides a method to validate HTTP response status codes.</p>
 *
 * <p>It also sets the base URI for all API calls using RestAssured, so
 * endpoints can be specified relative to the base URL.</p>
 */
public class ApiUtils {

    // Static block to initialize RestAssured base URI for all requests
    static {
        RestAssured.baseURI = ConfigManager.getBaseUrl();
    }

    /**
     * Sends a POST request to the given endpoint with the specified body.
     * Typically used for creating new resources (e.g., creating a Pet).
     *
     * @param endpoint The API endpoint (e.g., "/pet")
     * @param body     The object to send as JSON payload
     * @return Response object containing status code, headers, and body
     */
    public static Response post(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json") // Set request content type
                .header("api_key", ConfigManager.getApiKey())
                .body(body) // Set request body
                .post(endpoint); // Execute POST request
    }

    /**
     * Sends a GET request to the specified endpoint.
     * Typically used to retrieve resources by ID or query parameters.
     *
     * @param endpoint The API endpoint (e.g., "/pet/12345")
     * @return Response object containing status code, headers, and body
     */
    public static Response get(String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .header("api_key", ConfigManager.getApiKey())
                .get(endpoint); // Execute GET request
    }

    /**
     * Sends a PUT request to the given endpoint with the specified body.
     * Typically used to update existing resources (e.g., updating a Pet's status).
     *
     * @param endpoint The API endpoint (e.g., "/pet")
     * @param body     The object to send as JSON payload
     * @return Response object containing status code, headers, and body
     */
    public static Response put(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .header("api_key", ConfigManager.getApiKey()) // ensure API key is sent
                .body(body)
                .put(endpoint);
    }



    /**
     * Sends a DELETE request to the given endpoint.
     * Typically used to delete resources by ID (e.g., deleting a Pet).
     *
     * @param endpoint The API endpoint (e.g., "/pet/12345")
     * @return Response object containing status code, headers, and body
     */
    public static Response delete(String endpoint) {
        return given()
                .header("api_key", ConfigManager.getApiKey()) // Include API key for authorization
                .delete(endpoint); // Execute DELETE request
    }

    /**
     * Validates that the HTTP response status code matches the expected value.
     * Throws an AssertionError if the actual status code does not match.
     *
     * @param response The RestAssured Response object
     * @param expected The expected HTTP status code (e.g., 200, 404)
     */
    public static void validateStatusCode(Response response, int expected) {
        if(response.getStatusCode() != expected) {
            throw new AssertionError(
                    "Expected HTTP status code " + expected + " but got " + response.getStatusCode() +
                            " | Response body: " + response.getBody().asString()
            );
        }
    }
}
