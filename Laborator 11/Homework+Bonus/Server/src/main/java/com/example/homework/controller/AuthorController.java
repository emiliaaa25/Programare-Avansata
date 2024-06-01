package com.example.homework.controller;

import com.example.homework.model.Author;
import com.example.homework.repositories.AuthorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Api(value = "Author Management System", description = "Operations pertaining to authors in Author Management System")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping()
    @ApiOperation(value = "View a list of all authors")
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
