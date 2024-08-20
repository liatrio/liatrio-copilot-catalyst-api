package com.liatrio.dojo.devopsknowledgeshareapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthorController authorController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(authorController).isNotNull();
	}

	@Test
	public void getAuthors() throws Exception {
		mockMvc.perform(get("/authors"))
				.andExpect(status().isOk());
	}
}