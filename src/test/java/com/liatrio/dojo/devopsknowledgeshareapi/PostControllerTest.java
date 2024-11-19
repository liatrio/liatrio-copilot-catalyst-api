package com.liatrio.dojo.devopsknowledgeshareapi;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    PostController mockPost;

    @Mock
    PostRepository mockPr;

    private JSONObject testPost;

    @BeforeAll
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(mockPost).build();

        testPost = new JSONObject();

        testPost.put("firstName", "John");
        testPost.put("title", "My First Post");
        testPost.put("link", "https://www.example.com/blog/post-1");
    }

    @Test
    public void getPostsResponse() throws Exception {
        this.mockMvc.perform(get("/posts")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void postPostsFirstName() throws Exception {
        this.mockMvc.perform(
                post("/posts").content(testPost.toString()).with(csrf()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deletePostsFirstName() throws Exception {
        this.mockMvc.perform(
                post("/posts").content(testPost.toString()).with(csrf()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(delete("/posts/1/").with(csrf())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void updatePostSuccess() throws Exception {
        Post updatedPost = new Post();
        updatedPost.setFirstName("Jane");
        updatedPost.setTitle("Updated Post");
        updatedPost.setLink("https://www.example.com/blog/updated-post");
        updatedPost.setDatePosted("2023-10-10");
        updatedPost.setImageUrl("https://www.example.com/images/updated-post.jpg");

        when(mockPr.findById(1L)).thenReturn(Optional.of(new Post()));
        when(mockPr.save(any(Post.class))).thenReturn(updatedPost);

        JSONObject updatedPostJson = new JSONObject();
        updatedPostJson.put("firstName", "Jane");
        updatedPostJson.put("title", "Updated Post");
        updatedPostJson.put("link", "https://www.example.com/blog/updated-post");
        updatedPostJson.put("datePosted", "2023-10-10");
        updatedPostJson.put("imageUrl", "https://www.example.com/images/updated-post.jpg");

        this.mockMvc.perform(put("/posts/1")
                .content(updatedPostJson.toString())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updatePostNotFound() throws Exception {
        JSONObject updatedPostJson = new JSONObject();
        updatedPostJson.put("firstName", "Jane");
        updatedPostJson.put("title", "Updated Post");
        updatedPostJson.put("link", "https://www.example.com/blog/updated-post");
        updatedPostJson.put("datePosted", "2023-10-10");
        updatedPostJson.put("imageUrl", "https://www.example.com/images/updated-post.jpg");

        when(mockPr.findById(1L)).thenReturn(Optional.empty());

        this.mockMvc.perform(put("/posts/1")
                .content(updatedPostJson.toString())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updatePostBadRequest() throws Exception {
        JSONObject updatedPostJson = new JSONObject();
        updatedPostJson.put("firstName", "Jane");
        updatedPostJson.put("title", "Updated Post");
        updatedPostJson.put("link", "https://www.example.com/blog/updated-post");
        updatedPostJson.put("datePosted", "invalid-date");
        updatedPostJson.put("imageUrl", "https://www.example.com/images/updated-post.jpg");

        when(mockPr.findById(1L)).thenReturn(Optional.of(new Post()));

        this.mockMvc.perform(put("/posts/1")
                .content(updatedPostJson.toString())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
