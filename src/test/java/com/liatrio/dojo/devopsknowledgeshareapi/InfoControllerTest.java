package com.liatrio.dojo.devopsknowledgeshareapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString; // Add this import statement
import static org.hamcrest.Matchers.not; // Add this import statement

@SpringBootTest
@AutoConfigureMockMvc
public class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getInfo() throws Exception {
        mockMvc.perform(get("/info"))
               .andExpect(status().isOk())
               .andExpect(content().string("Welcome to the API version 1.0"));
    }

    @Test
    public void getInfo_NotSeeErrorMessage() throws Exception {
        mockMvc.perform(get("/info"))
               .andExpect(status().isOk())
               .andExpect(content().string(not(containsString("error"))));
    }
}
