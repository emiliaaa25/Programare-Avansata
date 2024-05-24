package com.example.Compulsory.controller;

import com.example.Compulsory.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Compulsory.repositories.AuthorRepository;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
