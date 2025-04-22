
package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "API for managing books in the bookstore")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books in the bookstore")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieves a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Get book by ISBN", description = "Retrieves a book by its ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Book> getBookByIsbn(
            @Parameter(description = "ISBN of the book to retrieve") @PathVariable String isbn) {
        try {
            return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/search/title")
    @Operation(summary = "Search books by title", description = "Retrieves books containing the given title")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getBooksByTitle(
            @Parameter(description = "Title to search for") @RequestParam String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }

    @GetMapping("/search/author")
    @Operation(summary = "Search books by author", description = "Retrieves books by the given author")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getBooksByAuthor(
            @Parameter(description = "Author to search for") @RequestParam String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Creates a new book in the bookstore")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<Book> createBook(
            @Parameter(description = "Book to create", required = true,
                    schema = @Schema(implementation = Book.class))
            @Valid @RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Updates an existing book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @Parameter(description = "Updated book details", required = true,
                    schema = @Schema(implementation = Book.class))
            @Valid @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return ResponseEntity.ok(updatedBook);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
