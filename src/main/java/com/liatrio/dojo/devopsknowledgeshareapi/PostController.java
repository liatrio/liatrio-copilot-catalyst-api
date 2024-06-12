package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class PostController {
    private PostRepository repository;

    private String deploymentType = System.getenv("DEPLOYMENT_TYPE") != null ? System.getenv("DEPLOYMENT_TYPE") : "blue";

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/posts")
    public Collection<Post> posts() {
        log.info("{}: recieved a GET request", deploymentType);
        return repository.findAll().stream().collect(Collectors.toList());
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
    public ResponseEntity<Post> putPost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return repository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setFirstName(updatedPost.getFirstName());
                    try {
                        post.setLink(updatedPost.getLink());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    post.setImageUrl(updatedPost.getImageUrl());
                    post.setDatePosted(new Date()); // Assuming you want to update the datePosted to the current date
                    Post savedPost = repository.save(post);
                    return new ResponseEntity<>(savedPost, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/posts/title/{title}")
    public Collection<Post> getPostsByTitle(@PathVariable String title) {
        log.info("{}: received a GET request for posts by title", deploymentType);
        return repository.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/posts/firstName/{firstName}")
    public Collection<Post> getPostsByFirstName(@PathVariable String firstName) {
        log.info("{}: received a GET request for posts by first name", deploymentType);
        return repository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @GetMapping("/posts/link/{link}")
    public Collection<Post> getPostsByLink(@PathVariable String link) {
        log.info("{}: received a GET request for posts by link", deploymentType);
        return repository.findByLinkContainingIgnoreCase(link);
    }
}
