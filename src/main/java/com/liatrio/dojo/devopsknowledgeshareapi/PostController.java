package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

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

    @GetMapping("/searchByTitle")
    public List<Post> getPostsByTitle(@RequestParam String title) {
        return repository.findByTitle(title);
    }

    @GetMapping("/searchByFirstName")
    public List<Post> getPostsByFirstName(@RequestParam String firstName) {
        return repository.findByFirstName(firstName);
    }

    @GetMapping("/searchByLink")
    public List<Post> getPostsByLink(@RequestParam String link) {
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
        log.info("{}: recieved a PUT request", deploymentType);
        return repository.findById(id)
                .map(post -> {
                    post.setFirstName(updatedPost.getFirstName());
                    post.setTitle(updatedPost.getTitle());
                    try {
                        post.setLink(updatedPost.getLink());
                    } catch (Exception e) {
                        log.error("Error setting link", e);
                        throw new RuntimeException(e);
                    }
                    post.setDatePosted(updatedPost.getDateAsDate());
                    post.setImageUrl(updatedPost.getImageUrl());
                    post.setDateUpdated(updatedPost.getDateAsDate());
                    return repository.save(post);
                })
                .orElseGet(() -> {
                    updatedPost.setId(id);
                    return repository.save(updatedPost);
                });
    }
}
