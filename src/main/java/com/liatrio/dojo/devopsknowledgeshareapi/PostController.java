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

    @GetMapping("/posts/title")
    public List<Post> getPostsByTitle(@RequestParam String title) {
        log.info("{}: received a GET request for posts by title", deploymentType);
        return repository.findByTitle(title);
    }

    @GetMapping("/posts/firstname")
    public List<Post> getPostsByFirstName(@RequestParam String firstName) {
        log.info("{}: received a GET request for posts by first name", deploymentType);
        return repository.findByFirstName(firstName);
    }

    @GetMapping("/posts/link")
    public List<Post> getPostsByLink(@RequestParam String link) {
        log.info("{}: received a GET request for posts by link", deploymentType);
        return repository.findByLink(link);
    }

    @PostMapping("/posts")
    public Post post(@RequestBody Post post, HttpServletResponse resp) {
        log.info("{}: recieved a POST request", deploymentType);
        return repository.save(post);
    }

    @PutMapping("/posts/{id}")
    public Post putPost(@PathVariable("id") Long id, @RequestBody Post updatedPost) throws Exception {
        log.info("{}: recieved a PUT request", deploymentType);
        return repository.findById(id)
                .map(post -> {
                    post.setFirstName(updatedPost.getFirstName());
                    post.setTitle(updatedPost.getTitle());
                    try {
                        post.setLink(updatedPost.getLink());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    post.setDatePosted(updatedPost.getDateAsDate());
                    post.setImageUrl(updatedPost.getImageUrl());
                    return repository.save(post);
                })
                .orElseGet(() -> {
                    updatedPost.setId(id);
                    return repository.save(updatedPost);
                });
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
