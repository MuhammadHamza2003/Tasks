package com.example.Rest.API.controller;

import com.example.Rest.API.entity.Entity;

import com.example.Rest.API.service.APIservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/books")
public class RestAPIController
{


    @Autowired
    private APIservice service;

    @GetMapping
    public List<Entity> getAll()
    {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Entity book)
    {
        try
        {
            service.saveEntity(book);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book created successfully with ISBN: " + book.getIsbn());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error creating book: " + e.getMessage());
        }
    }

    @GetMapping("/id:{id}")
    public Entity getBookById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/id:{id}")
    public boolean updateBookById(@PathVariable String id, @RequestBody Entity book)
    {
       service.saveEntity(book);
       return true;
    }

    @DeleteMapping("/id:{id}")
    public boolean deleteBookById(@PathVariable String id)
    {
        service.deleteById(id);
        return true;
    }
}
