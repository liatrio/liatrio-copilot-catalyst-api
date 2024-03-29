package com.liatrio.dojo.devopsknowledgeshareapi.functional;


import com.liatrio.dojo.devopsknowledgeshareapi.pojo.BlogPost;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HealthCheckTest {

    private static PropertiesExtractor propertiesExtractor;
    public static PrintStream printStream;
    public static RequestSpecification request;
    public static int blogId;
    static {
        try {

            //For extracting constant endpoints and constant url's from properties file
            propertiesExtractor = new PropertiesExtractor();
            //For the purpose of Logging
            printStream = new PrintStream(new FileOutputStream("logging.txt"), true);
            //Preparing the Common Spec request for POST,GET, DELETE
            request = new RequestSpecBuilder()
                    //.setBaseUri("http://localhost:8080")
                    .setBaseUri(propertiesExtractor.getProperty("spring.application.url"))
                    .setBasePath("/posts")
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                    .addHeader("Connection", "keep-alive")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .addFilter(ErrorLoggingFilter.logErrorsTo(printStream))
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Purpose: API request to perform an Independent 'GET' operation without depending on other methods
     * Outcome: Creates one BlogPost and Get's the list of Blogposts and deletes the blogPost at the end
     */
    @Test
    @Order(2)
    public void getRequest() throws IOException, JSONException {
        //Create a new blog post to run this method independent of another
        Response blogResponse = createBlogPost();
        JsonPath jsonPath = new JsonPath(blogResponse.asString());
        blogId = jsonPath.get("id");
        //Performing the Get Request for created Blog Id
        Response response = given().spec(request).get();
        JSONArray JSONResponseBody = new JSONArray(response.body().asString());
        for (int i = 0; i < JSONResponseBody.length(); i++) {
            JSONObject jsonobject = JSONResponseBody.getJSONObject(i);
            if (jsonobject.getInt("id") == blogId) {
                String name = jsonobject.getString("firstName");
                String title = jsonobject.getString("title");
                String link = jsonobject.getString("link");
                String imageUrl = jsonobject.getString("imageUrl");
                int id = jsonobject.getInt("id");
                Assertions.assertEquals("liatrio", name);
                Assertions.assertEquals("Testing", title);
                Assertions.assertEquals("http://www.liatrio.com", link);
                Assertions.assertEquals("http://www.liatrio.image.com", imageUrl);
                Assertions.assertEquals(blogId, id);
                //To check the output in command line terminal with gradle build you can uncomment and use it
                /* System.out.println("****************************");
                    System.out.println("Name::" + name);
                    System.out.println("title::" + title);
                    System.out.println("link::" + link);
                    System.out.println("****************************");
                */
            }//End If
        }//End For
        //Deleting the created Blog Post for Re-Usability and clear the database with un-necessary blog id's
        deleteBlogPost(blogId);
    }// End Method

    /**
     * Purpose: API request to perform a 'POST' operation for creating a BlogPost independently without depending on other
     * methods
     * Outcome: Creates a new BlogPost and grabs a created blogId and removes the record at the end for database cleanup
     */
    @Test
    @Order(1)
    public void  postRequest()  {
        //Leaving the below code commented to help the beginners how the request body is passed
        // as Json object in 'blogpost' pojo class by using Jackson,gson,johnzon libraries
        /*  String requestBody = "{\n" +
                "  \"firstName\": \"test2\",\n" +
                "  \"title\": \"string2\",\n" +
                "  \"link\": \"http://liatrio.com\",\n" +
                "  \"datePosted\": \"2022-04-07T16:28:30.813Z\",\n" +
                "  \"imageUrl\": \"http://liatrio.com\",\n" +
                "  \"dateAsDate\": \"2022-04-07T16:28:30.813Z\"\n" +
                "}";*/
        Response blogResponse = createBlogPost();
        Assertions.assertEquals(200, blogResponse.getStatusCode());
        JsonPath jsonPath = new JsonPath( blogResponse.asString());
        blogId = jsonPath.get("id");
        deleteBlogPost(blogId);
    }

    /**
     * Purpose: API request to perform a 'DELETE' operation in Deleting a BlogPost independently with out depending
     * on other methods
     * Outcome: Delete's the selected BlogId
     */
    @Test
    @Order(3)
    public void deleteRequest()  {
        //creating and Independent BlogPost
        Response blogResponse = createBlogPost();
        JsonPath jsonPath = new JsonPath( blogResponse.asString());
        blogId = jsonPath.get("id");
        //Grabbing the BlogId and deleting it
        Response  response = given().spec(request).delete(String.valueOf(blogId));
        Assertions.assertEquals(200, response.statusCode());
    }

    /**
     * Purpose: Re-Usable function for the purpose of creating Blogpost
     * @return Respsone Object
     */
    public Response createBlogPost(){
        BlogPost blogPost = new BlogPost();
        blogPost.setFirstName("liatrio");
        blogPost.setTitle("Testing");
        blogPost.setLink("http://www.liatrio.com");
        blogPost.setDatePosted("2022-04-12T19:47:43.915Z");
        blogPost.setImageUrl("http://www.liatrio.image.com");
        blogPost.setDateAsDate("2022-04-12T19:47:43.915Z");
        Response  response = given().spec(request).body(blogPost).post();
        return response;
    }

    /**
     * Purpose: Re-Usable function for the purpose of deleting blogId
     * @param blogId
     * @return blogId
     */
    public int deleteBlogPost(int blogId){
        Response  response = given().spec(request).delete(String.valueOf(blogId));
        return blogId;
    }


}
