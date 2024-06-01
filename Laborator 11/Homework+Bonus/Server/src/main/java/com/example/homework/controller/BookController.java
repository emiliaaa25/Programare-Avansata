package com.example.homework.controller;

import com.example.homework.model.Book;
import com.example.homework.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Api(value = "Book Management System", description = "Operations pertaining to books in Book Management System")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "View a list of all books")
    @GetMapping()
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @ApiOperation(value = "Get a book by its ID")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookRepository.findById(id);
    }

    @ApiOperation(value = "Add a new book")
    @PostMapping()
    public Book addBook(@RequestBody Book book){
        book.setId(bookRepository.findAll().size()+1);
        return bookRepository.add(book);
    }

    @ApiOperation(value = "Update an existing book")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestParam String name){
        Book book = bookRepository.findById(id);
        if (book == null) {
            return null;
        }
        book.setName(name);
        return bookRepository.update(id, name);
    }

    @ApiOperation(value = "Delete a book by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return new ResponseEntity<>(
                    "Product not found", HttpStatus.GONE);
        }
        bookRepository.delete(id);
        return new ResponseEntity<>("Product removed", HttpStatus.OK);
    }
}