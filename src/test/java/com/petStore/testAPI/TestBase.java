package com.petStore.testAPI;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import static io.restassured.RestAssured.*;

public class TestBase {


    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static RequestSpecification userSpec;
    public static RequestSpecification adminSpec;

    @BeforeClass
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://petstore.swagger.io/v2";

    }

    @AfterClass
    public static void close(){
        //reset the info we set above
        reset();
    }

}