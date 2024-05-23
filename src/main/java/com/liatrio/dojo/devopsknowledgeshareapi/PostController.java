package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.*;

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
        log.info("{}: received a GET request", deploymentType);
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/posts")
    public Post post(@RequestBody Post post, HttpServletResponse resp) {
        log.info("{}: received a POST request", deploymentType);
        return repository.save(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: received a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable("id") String id) {
        log.info("{}: received a GET request for post with id {}", deploymentType, id);
        return repository.findById(Long.parseLong(id)).orElse(null);
    }

    @GetMapping("/posts/search")
    public Collection<Post> searchPosts(@RequestParam("query") String query) {
        log.info("{}: received a GET request to search posts with query: {}", deploymentType, query);
        // WARNING: This code is vulnerable to SQL injection
        String sql = "SELECT * FROM posts WHERE title LIKE '%" + query + "%'";
        return repository.search(sql);
    }
}
