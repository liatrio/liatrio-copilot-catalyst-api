package com.liatrio.dojo.devopsknowledgeshareapi.functional;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InfoControllerTest {

    public static PrintStream printStream;
    public static RequestSpecification request;

    static {
        try {
            printStream = new PrintStream(new FileOutputStream("logging.txt"), true);
            request = new RequestSpecBuilder()
                    .setBaseUri(PropertiesExtractor.getProperty("spring.application.url"))
                    .setBasePath("/info")
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                    .addHeader("Connection", "keep-alive")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .addFilter(ErrorLoggingFilter.logErrorsTo(printStream))
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void testGetInfo() {
        Response response = given().spec(request).get();
        assertEquals(200, response.getStatusCode());
        assertEquals("Welcome to the API version 1.0", response.getBody().asString());
    }
}