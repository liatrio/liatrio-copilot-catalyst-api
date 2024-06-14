package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.ResponseEntity;
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
    public Post updatePost(@PathVariable Long id, @RequestBody Post newPost) {
        return repository.findById(id)
            .map(post -> {
                post.setTitle(newPost.getTitle());
                post.setFirstName(newPost.getFirstName());
                try {
                    post.setLink(newPost.getLink());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return repository.save(post);
            })
            .orElseGet(() -> {
                newPost.setId(id);
                return repository.save(newPost);
            });
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
