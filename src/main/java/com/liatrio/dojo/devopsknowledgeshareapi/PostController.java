package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.HttpStatus;
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

    @GetMapping("/posts/title/{title}")
    public Collection<Post> getPostsByTitle(@PathVariable String title) {
        return repository.findByTitle(title);
    }

    @GetMapping("/posts/firstName/{firstName}")
    public Collection<Post> getPostsByFirstName(@PathVariable String firstName) {
        return repository.findByFirstName(firstName);
    }

    @GetMapping("/posts/link/{link}")
    public Collection<Post> getPostsByLink(@PathVariable String link) {
        return repository.findByLink(link);
    }

    @PostMapping("/posts")
    public Post post(@RequestBody Post post, HttpServletResponse resp) {
        log.info("{}: recieved a POST request", deploymentType);
        return repository.save(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> putPost(@PathVariable("id") Long id, @RequestBody Post post) {
        log.info("{}: received a PUT request", deploymentType);
        return repository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(post.getTitle());
                    existingPost.setFirstName(post.getFirstName());
                    try {
                        existingPost.setLink(post.getLink());
                    } catch (Exception e) {
                        e.printStackTrace();
                        // handle exception
                    }
                    return new ResponseEntity<>(repository.save(existingPost), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        log.info("{}: recieved a DELETE request", deploymentType);
        repository.deleteById(Long.parseLong(id));
    }
}
