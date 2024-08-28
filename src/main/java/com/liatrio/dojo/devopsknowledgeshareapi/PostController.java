package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class PostController {
    private PostRepository repository;

    String deploymentType = System.getenv("DEPLOYMENT_TYPE") != null ? System.getenv("DEPLOYMENT_TYPE") : "blue";

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/posts")
    public Collection<Post> posts() {
        log.info("{}: recieved a GET request", deploymentType);
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/posts/title/{title}")
    public List<Post> getPostsByTitle(@PathVariable("title") String title) {
        log.info("{}: received a GET request for posts with title {}", deploymentType, title);
        return repository.findByTitle(title);
    }

    @GetMapping("/posts/firstname/{firstName}")
    public List<Post> getPostsByFirstName(@PathVariable("firstName") String firstName) {
        log.info("{}: received a GET request for posts with first name {}", deploymentType, firstName);
        return repository.findByFirstName(firstName);
    }

    @GetMapping("/posts/link/{link}")
    public List<Post> getPostsByLink(@PathVariable("link") String link) {
        log.info("{}: received a GET request for posts with link {}", deploymentType, link);
        return repository.findByLink(link);
    }

    @PostMapping("/posts")
    public Post post(@RequestBody Post post, HttpServletResponse resp) {
        log.info("{}: recieved a POST request", deploymentType);
        return repository.save(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post updatedPost) {
        log.info("{}: received a PUT request", deploymentType);
        return repository.findById(id).map(post -> {
            post.setFirstName(updatedPost.getFirstName());
            post.setTitle(updatedPost.getTitle());
            try {
                post.setLink(updatedPost.getLink());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Convert String to Date
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the pattern as needed
                Date datePosted = formatter.parse(updatedPost.getDatePosted());
                post.setDatePosted(datePosted);
            } catch (ParseException e) {
                log.error("Date parsing error: ", e);
                // Handle the error appropriately, e.g., throw an exception or set a default date
            }
            post.setImageUrl(updatedPost.getImageUrl());
            post.setDateAsDate(updatedPost.getDateAsDate());
            return repository.save(post);
        }).orElseGet(() -> {
            updatedPost.setId(id);
            return repository.save(updatedPost);
        });
    }
}
