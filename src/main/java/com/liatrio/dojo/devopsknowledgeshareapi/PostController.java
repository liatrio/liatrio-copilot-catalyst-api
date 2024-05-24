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
        log.info("{}: recieved a GET request", deploymentType);
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/posts")
    public Post post(@RequestBody Post post, HttpServletResponse resp) {
        log.info("{}: recieved a POST request", deploymentType);
        return repository.save(post);
    }

    @PutMapping("/posts/{id}")
    public Post putPost(@PathVariable("id") String id, @RequestBody Post updatedPost) throws Exception {
        log.info("{}: received a PUT request", deploymentType);
        Post existingPost = repository.findById(Long.parseLong(id)).orElse(null);
        if (existingPost != null) {
            existingPost.setFirstName(updatedPost.getFirstName());
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setLink(updatedPost.getLink());
            return repository.save(existingPost);
        } else {
            throw new IllegalArgumentException("Post not found with id: " + id);
        }
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
