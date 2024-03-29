package com.liatrio.dojo.devopsknowledgeshareapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PostTest {

    Long id = 12345678910L;
    String name = "jon";
    String title = "devops";
    String link = "https://devops.com/blog/post-1";
    String imageUrl = "https://devops.com/images/image1.png";


    @Test
    public void getIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        assertEquals(id, test);
    }

    @Test
    public void getBadIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        Long badVal = 12345678912L;
        assertNotEquals(badVal, test);
    }

    @Test
    public void setIdTest() throws Exception {
        Post hc = new Post();
        hc.setId(id);
        Long test = hc.getId();
        assertEquals(id, test);
    }

    @Test
    public void setNameTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        String test = hc.getFirstName();
        assertEquals(name, test);
    }

    @Test
    public void getNameTest() throws Exception {
        Post hc = new Post();
        hc.setFirstName(name);
        String test = hc.getFirstName();
        assertEquals(name, test);
    }

    @Test
    public void setTitleTest() throws Exception {
        Post hc = new Post();
        hc.setTitle(title);
        String test = hc.getTitle();
        assertEquals(title, test);
    }

    @Test
    public void getTitleTest() throws Exception {
        Post hc = new Post();
        hc.setTitle(title);
        String test = hc.getTitle();
        assertEquals(title, test);
    }

    @Test
    public void setLinkTest() throws Exception {
        Post hc = new Post();
        hc.setLink(link);
        String test = hc.getLink();
        assertEquals(link, test);
    }

    @Test
    public void getLinkTest() throws Exception {
        Post hc = new Post();
        hc.setLink(link);
        String test = hc.getLink();
        assertEquals(link, test);
    }

    @Test
    public void setImageUrlTest() throws Exception {
        Post hc = new Post();
        hc.setImageUrl(imageUrl);
        String test = hc.getImageUrl();
        assertEquals(imageUrl, test);
    }

    @Test
    public void getImageUrlTest() throws Exception {
        Post hc = new Post();
        hc.setImageUrl(imageUrl);
        String test = hc.getImageUrl();
        assertEquals(imageUrl, test);
    }
}
