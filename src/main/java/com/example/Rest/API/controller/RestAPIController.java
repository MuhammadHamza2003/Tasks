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
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class RestAPIController {

    @Autowired
    private APIservice service;

    /**
     * Get all books from the database
     * @return List of all books
     */
    @GetMapping
    public ResponseEntity<List<Entity>> getAllBooks() {
        try {
            List<Entity> books = service.getAll();
            if (books.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create a new book
     * @param book Book entity to be created
     * @return Success or error message
     */
    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Entity book) {
        try {
            if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: ISBN cannot be null or empty");
            }
            
            // Check if book already exists
            Entity existingBook = service.findById(book.getIsbn());
            if (existingBook != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: Book with ISBN " + book.getIsbn() + " already exists");
            }
            
            service.saveEntity(book);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book created successfully with ISBN: " + book.getIsbn());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating book: " + e.getMessage());
        }
    }

    /**
     * Get a book by its ISBN
     * @param isbn Book ISBN
     * @return Book entity or 404 if not found
     */
    @GetMapping("/id:{isbn}")
    public ResponseEntity<Entity> getBookById(@PathVariable String isbn) {
        try {
            Entity book = service.findById(isbn);
            if (book == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update an existing book
     * @param isbn Book ISBN
     * @param book Updated book entity
     * @return Success or error message
     */
    @PutMapping("/id:{isbn}")
    public ResponseEntity<String> updateBookById(@PathVariable String isbn, @RequestBody Entity book) {
        try {
            // Check if book exists
            Entity existingBook = service.findById(isbn);
            if (existingBook == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Book with ISBN " + isbn + " not found");
            }
            
            // Set the ISBN to ensure consistency
            book.setIsbn(isbn);
            service.saveEntity(book);
            
            return ResponseEntity.ok("Book updated successfully with ISBN: " + isbn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating book: " + e.getMessage());
        }
    }

    /**
     * Delete a book by its ISBN
     * @param isbn Book ISBN
     * @return Success or error message
     */
    @DeleteMapping("/id:{isbn}")
    public ResponseEntity<String> deleteBookById(@PathVariable String isbn) {
        try {
            // Check if book exists
            Entity existingBook = service.findById(isbn);
            if (existingBook == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Book with ISBN " + isbn + " not found");
            }
            
            service.deleteById(isbn);
            return ResponseEntity.ok("Book deleted successfully with ISBN: " + isbn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting book: " + e.getMessage());
        }
    }
}
