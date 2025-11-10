package com.petstore.stepdefinitions;

import com.petstore.models.Pet;
import com.petstore.utils.ApiUtils;
import com.petstore.utils.DataGenerator;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.*;

public class PetSteps {

    private Pet pet;
    private Response response;
    private final int maxRetries = 5; // Max attempts for GET requests
    private final long retryDelay = 1000; // 1 second

    /**
     * Creates a new pet using test data.
     * Supports dynamic Pet IDs to avoid conflicts.
     * @param index Index from test data file
     */
    @Given("I create a new pet from test data index {int}")
    public void createPet(int index) throws Exception {
        pet = DataGenerator.getPetFromFile(index); // Fetch dynamic test data
        response = ApiUtils.post("/pet", pet);
        ApiUtils.validateStatusCode(response, 200);
        // Map response to pet object
        pet = response.as(Pet.class);
        Thread.sleep(500); // small delay to allow backend processing
    }

    /**
     * Retrieves the pet by ID with retry logic to handle propagation delays.
     */
    @When("I retrieve the pet by ID")
    public void getPet() throws InterruptedException {
        int attempts = 0;
        while (attempts < maxRetries) {
            response = ApiUtils.get("/pet/" + pet.getId());
            if (response.getStatusCode() == 200) {
                Pet retrieved = response.as(Pet.class);

                // Correct comparisons
                boolean idMatch = retrieved.getId() == pet.getId(); // for long/Long
                boolean nameMatch = retrieved.getName().equals(pet.getName());
                boolean statusMatch = retrieved.getStatus().equals(pet.getStatus());

                if (idMatch && nameMatch && statusMatch) {
                    return; // Successful retrieval
                }
            }
            attempts++;
            Thread.sleep(retryDelay);
        }
        throw new AssertionError("Pet not found or status mismatch after " + maxRetries + " attempts");
    }


    /**
     * Updates the pet status and validates response.
     */
    @When("I update the pet status to {string}")
    public void updatePet(String newStatus) throws InterruptedException {
        pet.setStatus(newStatus);
        response = ApiUtils.put("/pet", pet);
        ApiUtils.validateStatusCode(response, 200);
        Thread.sleep(500);
    }

    /**
     * Finds pets by status
     */
    @When("I find pets by status {string}")
    public void findPets(String status) {
        response = ApiUtils.get("/pet/findByStatus?status=" + status);
        ApiUtils.validateStatusCode(response, 200);
    }

    /**
     * Deletes the pet
     */
    @When("I delete the pet")
    public void deletePet() {
        response = ApiUtils.delete("/pet/" + pet.getId());
        ApiUtils.validateStatusCode(response, 200);
    }

    /**
     * Validates pet JSON schema
     */
    @Then("the response matches the Pet schema")
    public void validatePetSchema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/pet-schema.json"));
    }

    /**
     * Validates error JSON schema
     */
    @Then("the response matches the Error schema")
    public void validateErrorSchema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/error-schema.json"));
    }

    /**
     * Cleanup to ensure pets are removed after tests
     */
    @After
    public void cleanup() {
        if (pet != null) { // only attempt deletion if pet exists
            try {
                ApiUtils.delete("/pet/" + pet.getId());
                System.out.println("Deleted pet with ID: " + pet.getId());
            } catch (Exception e) {
                // ignore if already deleted or not found
                e.printStackTrace();
            } finally {
                pet = null; // clear reference after cleanup
            }
        }
    }}

