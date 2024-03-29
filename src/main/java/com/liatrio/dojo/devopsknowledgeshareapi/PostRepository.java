package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
interface PostRepository extends JpaRepository<Post, Long> {
}
