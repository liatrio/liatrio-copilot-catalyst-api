package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.Optional;

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
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post postDetails) {
        log.info("{}: received a PUT request", deploymentType);
        Optional<Post> optionalPost = repository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setFirstName(postDetails.getFirstName());
            post.setTitle(postDetails.getTitle());
            try {
                post.setLink(postDetails.getLink());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(postDetails.getDatePosted());
                post.setDatePosted(date);
            } catch (ParseException e) {
                return ResponseEntity.badRequest().body(null);
            }
            post.setImageUrl(postDetails.getImageUrl());
            post.setDateAsDate(postDetails.getDateAsDate());
            final Post updatedPost = repository.save(post);
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
