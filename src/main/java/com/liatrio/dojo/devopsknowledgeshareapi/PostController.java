package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.http.HttpStatus;

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
    public ResponseEntity<Post> putPost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> optionalPost = repository.findById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            try {
                existingPost.setLink(post.getLink());
            } catch (Exception e) {
                // Handle the exception here
            }
            existingPost.setFirstName(post.getFirstName());
            log.info("{}: received a PUT request to update post with id {}", deploymentType, id);
            return new ResponseEntity<>(repository.save(existingPost), HttpStatus.OK);
        } else {
            log.info("{}: received a PUT request for non-existing post with id {}", deploymentType, id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
