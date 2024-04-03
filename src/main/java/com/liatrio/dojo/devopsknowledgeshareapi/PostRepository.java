package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
@Repository
interface PostRepository extends JpaRepository<Post, Long> {

     // Add your custom methods here

    // Example: Find posts by title
    List<Post> findByTitle(String title);

    // Find posts by author's first name
    List<Post> findByAuthorFirstName(String firstName);

    // Find posts by link
    List<Post> findByLink(String link);
}
