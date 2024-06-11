package com.liatrio.dojo.devopsknowledgeshareapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.liatrio.dojo.devopsknowledgeshareapi.controller.AuthorController;
import com.liatrio.dojo.devopsknowledgeshareapi.model.Author;
import com.liatrio.dojo.devopsknowledgeshareapi.service.AuthorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    public void testGetAuthors() {
        Author author1 = new Author();
        author1.setName("Author 1");
        Author author2 = new Author();
        author2.setName("Author 2");

        List<Author> authors = Arrays.asList(author1, author2);

        when(authorService.getAllAuthors()).thenReturn(authors);

        ResponseEntity<List<Author>> response = authorController.getAuthors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Author 1", response.getBody().get(0).getName());
        assertEquals("Author 2", response.getBody().get(1).getName());
    }
}
