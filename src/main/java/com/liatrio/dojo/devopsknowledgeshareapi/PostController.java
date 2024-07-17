package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;
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
    public ResponseEntity<Post> putPost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return repository.findById(id)
                .map(post -> {
                    post.setFirstName(updatedPost.getFirstName());
                    post.setTitle(updatedPost.getTitle());
                    try {
                        post.setLink(updatedPost.getLink());
                    } catch (Exception e) {
                        return new ResponseEntity<Post>(HttpStatus.BAD_REQUEST);
                    }
                    post.setImageUrl(updatedPost.getImageUrl());
                    post.setDatePosted(Calendar.getInstance().getTime());
                    Post savedPost = repository.save(post);
                    return new ResponseEntity<Post>(savedPost, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<Post>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
