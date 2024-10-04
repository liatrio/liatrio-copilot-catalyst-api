package com.liatrio.dojo.devopsknowledgeshareapi.functional;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class InfoControllerTest {

    private static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    @Test
    public void testInfoPage() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/info")
                .then()
                .statusCode(200)
                .body(equalTo("Welcome to the API version 1.0"))
                .extract()
                .response();

        // Ensure no error message is present
        response.then().body("error", equalTo(null));
    }
}