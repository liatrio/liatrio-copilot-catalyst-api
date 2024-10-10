package com.liatrio.dojo.devopsknowledgeshareapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    AuthorController mockAuthorController;

    @BeforeAll
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(mockAuthorController).build();
    }

    @Test
    public void getAuthorsResponse() throws Exception {
        this.mockMvc.perform(get("/authors"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }
}
