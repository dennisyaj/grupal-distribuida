package com.distribuida.appauthors.controller;

import com.distribuida.appauthors.model.Author;
import com.distribuida.appauthors.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorDao rep;

    @GetMapping
    public List<Author> findAll() {
        System.out.println("**************************");

        return (List<Author>) rep.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Optional<Author> optionalAuthor = rep.findById(id);
        if (optionalAuthor.isPresent()) {
            return ResponseEntity.ok(optionalAuthor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return rep.save(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Integer id, @RequestBody Author author) {
        Author existingAuthor = rep.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setFirstName(author.getFirstName());
            existingAuthor.setLastName(author.getLastName());
            rep.save(existingAuthor);
            return ResponseEntity.ok(existingAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        rep.deleteById(id);
    }
}

