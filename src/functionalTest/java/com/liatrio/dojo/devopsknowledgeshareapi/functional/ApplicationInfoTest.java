package com.liatrio.dojo.devopsknowledgeshareapi.functional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.annotation.DirtiesContext;
// import org.springframework.test.context.TestMethodOrder;
// import org.springframework.test.context.junit.jupiter.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ApplicationInfoTest {

    /**
     * Test case for verifying that the /info page shows the correct API version information
     * without requiring the user to be logged in.
     */
    @Test
    @Order(1)
    public void getInfoPageAsUnauthenticatedUser() {
        // Declare and initialize the request variable
        RequestSpecification request = given();
        
        request
            .when()
            .get("/info")
            .then()
            .statusCode(200)
            .body(containsString("Welcome to the API version 1.0"))
            .body(not(containsString("error")));
    }
}