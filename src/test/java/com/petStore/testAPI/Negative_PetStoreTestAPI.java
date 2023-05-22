package com.petStore.testAPI;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Negative_PetStoreTestAPI extends TestBase {

    // InvalidID updated

    @Test
    public void putRequest() {
        String requestBody = "{\n" +
                "  \"id\":45641644612L,\n" +
                "  \"category\": {\n" +
                "    \"id\": 250,\n" +
                "    \"name\": \"Golden Retriever\"\n" +
                "  },\n" +
                "  \"name\": \"Jojo\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(400)
                .log().all();
    }

    //Pet not found is displayed
    @Test
    public void getPetWithID() {
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("petId", 456)
                .get("/pet/{petId}")
                .then()
                .statusCode(404)
                .body("message", is("Pet not found"))
                .contentType(ContentType.JSON)
                .log().all();


    }

    //Delete invalid ID pet
    @Test
    public void deletePet() {
        given().pathParam("id", 456543154612L)
                .when().delete("/pet/{id}")
                .then()
                .statusCode(404)
                .log().all();


    }


}
