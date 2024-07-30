package com.liatrio.dojo.devopsknowledgeshareapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class InfoControllerTest {
    // User visits the /info URL of the service
    // Given the user is not logged in
    // When the user visits the /info page
    // Then the user should see the phrase "Welcome to the API version 1.0""
    // And they should not see an error message

    @WebMvcTest(InfoController.class)
    public class InfoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testInfoPageNotLoggedIn() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/info"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Welcome to the API version 1.0"))
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("error"))));
        }

        @Test
        public void testInfoPageLoggedIn() throws Exception {
            // Additional test case for when the user is logged in
            // Implement the test logic here
        }

        @Test
        public void testInfoPageError() throws Exception {
            // Additional test case for when an error occurs on the /info page
            // Implement the test logic here
        }

        // Add more test cases as needed

    }
}
