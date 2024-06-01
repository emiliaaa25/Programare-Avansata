package com.example.client.controller;

import com.example.Client.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {

    private static final String uri = "http://localhost:1520/books";
    private static WebClient webClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getBooksFlux() {
        Flux<Book> bookFlux = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Book.class);
       return bookFlux;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> getBook(@PathVariable long id) {
        return webClient.get()
                .uri(uri+"/{id}", id)
                .retrieve()
                .bodyToMono(Book.class);
    }

    @PostMapping
    public static Book createBook(String name, String authors, Long year, String genre, String language) {
        String requestUri = uri + "?" +
                "name=" + name+ "&" +
                "authors=" +authors + "&" +
                "year=" + year + "&" +
                "genres=" + genre + "&" +
                "language=" +language;

        return WebClient.create()
                .post()
                .uri(requestUri)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Book.class).block();
    }


    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestParam String name){
        String requestUri = uri + "/" + id + "?" +
                "name=" + name + "&" ;
        return WebClient.create()
                .put()
                .uri(requestUri)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    @DeleteMapping("/{id}")
    public Void deleteBook(@PathVariable long id){
        return WebClient.create()
                .delete()
                .uri(uri + "/" + id)
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }
}
