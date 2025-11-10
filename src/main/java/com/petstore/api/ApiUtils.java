package com.petstore.api;

import com.petstore.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Utility class for making API calls to the PetStore API.
 * This class provides reusable methods for CRUD operations on API endpoints.
 * It handles common configurations like base URL, API key header, and content type.
 */
public class ApiUtils {

    /**
     * Sends a POST request to the specified endpoint with the given request body.
     * Used for creating resources like a new Pet.
     *
     * @param endpoint API endpoint (e.g., "/pet")
     * @param body     Request body object (e.g., Pet object)
     * @return Response object containing status code, headers, and response body
     */
    public static Response post(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON) // Set request content type to JSON
                .header("api_key", ConfigManager.getApiKey()) // Add API key header
                .body(body) // Set request body
                .post(ConfigManager.getBaseUrl() + endpoint) // Send POST request
                .then().extract().response(); // Extract and return response
    }

    /**
     * Sends a PUT request to the specified endpoint with the given request body.
     * Used for updating existing resources.
     *
     * @param endpoint API endpoint (e.g., "/pet")
     * @param body     Request body object (e.g., Pet object)
     * @return Response object containing status code, headers, and response body
     */
    public static Response put(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .header("api_key", ConfigManager.getApiKey())
                .body(body)
                .put(ConfigManager.getBaseUrl() + endpoint)
                .then().extract().response();
    }

    /**
     * Sends a GET request to the specified endpoint.
     * Used for retrieving resources like a Pet by ID.
     *
     * @param endpoint API endpoint (e.g., "/pet/12345")
     * @return Response object containing status code, headers, and response body
     */
    public static Response get(String endpoint) {
        return given()
                .header("api_key", ConfigManager.getApiKey())
                .get(ConfigManager.getBaseUrl() + endpoint)
                .then().extract().response();
    }

    /**
     * Sends a DELETE request to the specified endpoint.
     * Used for deleting resources like a Pet by ID.
     *
     * @param endpoint API endpoint (e.g., "/pet/12345")
     * @return Response object containing status code, headers, and response body
     */
    public static Response delete(String endpoint) {
        return given()
                .header("api_key", ConfigManager.getApiKey())
                .delete(ConfigManager.getBaseUrl() + endpoint)
                .then().extract().response();
    }

    /**
     * Validates that the response status code matches the expected value.
     * Throws an AssertionError if the status code is not as expected.
     *
     * @param response Response object from API call
     * @param expected Expected HTTP status code (e.g., 200, 404)
     */
    public static void validateStatusCode(Response response, int expected) {
        if (response.getStatusCode() != expected) {
            throw new AssertionError(
                    "Expected: " + expected +
                            " but got: " + response.getStatusCode() +
                            " | Response body: " + response.getBody().asString()
            );
        }
    }
}
