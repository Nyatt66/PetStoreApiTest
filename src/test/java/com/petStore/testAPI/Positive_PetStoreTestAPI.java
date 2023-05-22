package com.petStore.testAPI;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Positive_PetStoreTestAPI extends TestBase {

    long idFromPost;


    // Create Pet
    @Test
    public void postPet() {
        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
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


        idFromPost = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestBody).log().all()
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath().getLong("id");

    }


// Update pet. Category "id" and "name" updated.

    @Test (dependsOnMethods = "postPet")
    public void putRequest() {
        String requestBody = "{\n" +
                "  \"id\":"+idFromPost+",\n" +
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
                .statusCode(200)
                .body("category.id", is(250))
                .body("category.name", is("Golden Retriever"))
                .log().all();
    }
    // Read Pet
    @Test(dependsOnMethods = "putRequest")
    public void getPetWithID() {
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("petId", idFromPost)
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", is("Jojo"))
                .body("id", is(idFromPost))
                .log().all();

    }

    //Delete pet
    @Test(dependsOnMethods = "getPetWithID")
    public void deletePet(){

        given().pathParam("id",idFromPost)
                .when().delete("/pet/{id}")
                .then().statusCode(200);


    }


}
