// package com.liatrio.dojo.devopsknowledgeshareapi;

// import io.restassured.path.json.JsonPath;
// import io.restassured.response.Response;
// import org.junit.jupiter.api.*;
// import io.restassured.specification.RequestSpecification;
// import io.restassured.builder.RequestSpecBuilder;
// import io.restassured.filter.log.RequestLoggingFilter;
// import io.restassured.filter.log.ResponseLoggingFilter;
// import io.restassured.filter.log.ErrorLoggingFilter;
// import org.springframework.boot.test.context.SpringBootTest;

// import static io.restassured.RestAssured.given;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBootTest
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// public class InfoControllerTest {

//     private static RequestSpecification request;

//     @BeforeAll
//     public static void setup() {
//         request = new RequestSpecBuilder()
//                 .setBaseUri("http://localhost:8080")
//                 .addFilter(new RequestLoggingFilter())
//                 .addFilter(new ResponseLoggingFilter())
//                 .addFilter(new ErrorLoggingFilter())
//                 .build();
//     }

//     /**
//      * Scenario: User visits the /info URL of the service
//      * Given the user is not logged in
//      * When the user visits the /info page
//      * Then the user should see the phrase "Welcome to the API version 1.0"
//      * And they should not see an error message
//      */
//     @Test
//     @Order(1)
//     public void userVisitsInfoPage() {
//         Response response = given().spec(request).get("/info");
//         assertEquals(200, response.getStatusCode());

//         JsonPath jsonPath = new JsonPath(response.asString());
//         String message = jsonPath.getString("message");
//         assertEquals("Welcome to the API version 1.0", message);
//     }
// }
