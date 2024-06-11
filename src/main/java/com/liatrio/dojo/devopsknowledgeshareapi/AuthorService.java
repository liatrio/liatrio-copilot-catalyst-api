package com.liatrio.dojo.devopsknowledgeshareapi;

import com.liatrio.dojo.devopsknowledgeshareapi.model.Author;
import com.liatrio.dojo.devopsknowledgeshareapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}