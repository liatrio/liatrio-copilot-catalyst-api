package com.liatrio.dojo.devopsknowledgeshareapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorControllerTest {

    @Autowired
    private AuthorController authorController;

    @MockBean
    private AuthorService authorService;

    @Test
    public void testGetAuthors() {
        Author author1 = new Author("Author1", "Bio1");
        Author author2 = new Author("Author2", "Bio2");
        List<Author> authors = Arrays.asList(author1, author2);

        Mockito.when(authorService.getAuthors()).thenReturn(authors);

        List<Author> result = authorController.getAuthors();

        assertEquals(authors, result);
    }

    @Test
    public void testGetAuthorById() {
        Author author = new Author("Author1", "Bio1");

        Mockito.when(authorService.getAuthorById(1L)).thenReturn(author);

        Author result = authorController.getAuthorById(1L);

        assertEquals(author, result);
    }

    // Add more tests as needed
}
