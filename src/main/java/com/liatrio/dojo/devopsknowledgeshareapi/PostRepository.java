package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
@Repository
interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByTitle(String title);
  List<Post> findByFirstName(String firstName);
  List<Post> findByLink(String link);
}
