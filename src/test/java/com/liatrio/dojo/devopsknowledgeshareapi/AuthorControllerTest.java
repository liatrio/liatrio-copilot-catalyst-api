package com.liatrio.dojo.devopsknowledgeshareapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Existing test
    @Test
    public void testGetAllAuthors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // New test for the pseudo code comment
    @Test
    public void testGetAuthorById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // New test for the pseudo code comment
    @Test
    public void testGetAuthorByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", 999))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}