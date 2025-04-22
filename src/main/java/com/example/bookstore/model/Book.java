package com.example.bookstore.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books", schema="mycrud")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Book entity representing a book in the bookstore")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the book", example = "1")
    private Long id;

    @NotBlank(message = "Title is required")
    @Schema(description = "Title of the book", example = "The Great Gatsby", required = true)
    private String title;

    @NotBlank(message = "Author is required")
    @Schema(description = "Author of the book", example = "F. Scott Fitzgerald", required = true)
    private String author;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN must be valid")
    @Schema(description = "ISBN of the book", example = "978-3-16-148410-0", required = true)
    private String isbn;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Schema(description = "Price of the book", example = "19.99", required = true)
    private Double price;

    @Schema(description = "Description of the book", example = "A novel about the American Dream")
    private String description;

    @Schema(description = "Publication date of the book", example = "2023-01-15")
    private LocalDate publicationDate;

    @Schema(description = "Indicates if the book is currently in stock", example = "true")
    private boolean inStock = true;
}