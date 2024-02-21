package com.distribuida.appbooks.controller;

import com.distribuida.appbooks.clients.AuthorRestClient;
import com.distribuida.appbooks.dtos.BookDto;
import com.distribuida.appbooks.model.Book;
import com.distribuida.appbooks.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private AuthorRestClient authorRestClient;

    @Autowired
    private BookDao repo;

    @GetMapping
    public List<BookDto> findAll() {
        return repo.findAll().stream()
                .map(book->{
                    System.out.println("book.getAuthorId: " + book.getAuthorId());
                    var author = authorRestClient.findById(book.getAuthorId());
                    System.out.println("author: " + author);
                    var dto = BookDto.from(book);

                    String aname = String.format("%s %s",
                            author.getLastName(), author.getFirstName());

                    dto.setAuthorName( aname );

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getAuthorById(@PathVariable Integer id) {
        Optional<Book> optionalBook = repo.findById(id);
        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return repo.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Integer id, @RequestBody Book book) {
        Book existingBook = repo.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setIsbn(book.getIsbn());
            existingBook.setTitle(book.getTitle());
            existingBook.setPrice(book.getPrice());
            repo.save(existingBook);
            return ResponseEntity.ok(existingBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
