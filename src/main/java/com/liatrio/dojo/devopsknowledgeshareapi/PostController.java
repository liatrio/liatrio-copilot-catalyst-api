package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
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
        try {
            log.info("{}: recieved a POST request", deploymentType);
            return repository.save(post);
        } catch (Exception e) {
            // Handle the exception here
            log.error("An error occurred while saving the post.", e);
            // return an appropriate response or throw a custom exception
            throw new RuntimeException("An error occurred while saving the post.", e);
        }
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> putPost(@PathVariable Long id, @RequestBody Post postDetails) {
        Optional<Post> optionalPost = repository.findById(id);
    
        if (!optionalPost.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Post post = optionalPost.get();
        post.setFirstName(postDetails.getFirstName());
        post.setTitle(postDetails.getTitle());
        try {
            post.setLink(postDetails.getLink());
        } catch (Exception e) {
            // Handle the exception here
            log.error("An error occurred while setting the link.", e);
            // return an appropriate response or throw a custom exception
            throw new RuntimeException("An error occurred while setting the link.", e);
        }
        // set other fields as needed
    
        repository.save(post);
    
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
