package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;
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

    @GetMapping("/posts/title")
    public Collection<Post> getPostsByTitle(@RequestParam("title") String title) {
        log.info("{}: received a GET request for posts by title", deploymentType);
        return repository.findByTitle(title);
    }

    @GetMapping("/posts/firstname")
    public Collection<Post> getPostsByFirstName(@RequestParam("firstName") String firstName) {
        log.info("{}: received a GET request for posts by first name", deploymentType);
        return repository.findByFirstName(firstName);
    }

    @GetMapping("/posts/link")
    public Collection<Post> getPostsByLink(@RequestParam("link") String link) {
        log.info("{}: received a GET request for posts by link", deploymentType);
        return repository.findByLink(link);
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

    @PutMapping("/posts/{id}")
    public Post putPost(@PathVariable("id") Long id, @RequestBody Post updatedPost) {
        log.info("{}: received a PUT request", deploymentType);
        Optional<Post> optionalPost = repository.findById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setFirstName(updatedPost.getFirstName());
            existingPost.setTitle(updatedPost.getTitle());
            try {
                existingPost.setLink(updatedPost.getLink());
            } catch (Exception e) {
                e.printStackTrace();
            }
            existingPost.setDatePosted(updatedPost.getDateAsDate());
            existingPost.setImageUrl(updatedPost.getImageUrl());
            return repository.save(existingPost);
        } else {
            throw new RuntimeException("Post not found with id " + id);
        }
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
