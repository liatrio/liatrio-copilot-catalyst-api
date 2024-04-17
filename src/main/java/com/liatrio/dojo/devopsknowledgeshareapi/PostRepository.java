package com.liatrio.dojo.devopsknowledgeshareapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findByTitle(String title);
  List<Post> findByFirstName(String firstName);
  List<Post> findByLink(String link);
}
