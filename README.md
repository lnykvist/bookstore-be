
# Bookstore API

A Spring Boot REST API for managing a bookstore inventory.

## Table of Contents

- [Overview](#overview)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Data Model](#data-model)
- [Error Handling](#error-handling)
- [Examples](#examples)

## Overview

This application provides a RESTful API for managing books in a bookstore. It allows for creating, reading, updating, and deleting book records, as well as searching for books by various criteria.

## Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- Jakarta Persistence
- Jakarta Validation
- PostgreSQL/MySQL (database)
- Maven/Gradle (build tool)

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven or Gradle
- PostgreSQL/MySQL database

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/bookstore-api.git
   cd bookstore-api
   ```

2. Configure the database connection in `application.properties` or `application.yml`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
   spring.datasource.username=postgres
   spring.datasource.password=password
   ```

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   or with Gradle:
   ```bash
   ./gradlew bootRun
   ```

4. The API will be available at `http://localhost:8080/api/books`

## API Endpoints

| Method | URL                          | Description                                | Request Body | Response                  |
|--------|------------------------------|--------------------------------------------|--------------|-----------------------------|
| GET    | /api/books                   | Get all books                              | -            | Array of Book objects       |
| GET    | /api/books/{id}              | Get a book by ID                           | -            | Book object                 |
| GET    | /api/books/isbn/{isbn}       | Get a book by ISBN                         | -            | Book object                 |
| GET    | /api/books/search/title?title={title} | Search books by title             | -            | Array of Book objects       |
| GET    | /api/books/search/author?author={author} | Search books by author         | -            | Array of Book objects       |
| POST   | /api/books                   | Create a new book                          | Book object  | Created Book object         |
| PUT    | /api/books/{id}              | Update an existing book                    | Book object  | Updated Book object         |
| DELETE | /api/books/{id}              | Delete a book                              | -            | No content (204)            |

## Data Model

### Book

| Field           | Type        | Description                                      | Constraints                  |
|-----------------|-------------|--------------------------------------------------|------------------------------|
| id              | Long        | Unique identifier                                | Auto-generated               |
| title           | String      | Book title                                       | Not blank, required          |
| author          | String      | Book author                                      | Not blank, required          |
| description     | String      | Book description                                 | Max 1000 characters          |
| isbn            | String      | International Standard Book Number               | Not blank, unique, required  |
| publicationDate | LocalDate   | Date when the book was published                 | Not null, required           |
| price           | BigDecimal  | Book price                                       | Positive value, required     |
| inStock         | boolean     | Availability status                              | Default: true                |

## Error Handling

The API uses standard HTTP status codes to indicate the success or failure of requests:

- `200 OK`: The request was successful
- `201 Created`: A new resource was successfully created
- `204 No Content`: The request was successful but there is no representation to return
- `400 Bad Request`: The request could not be understood or was missing required parameters
- `404 Not Found`: Resource was not found
- `500 Internal Server Error`: An error occurred on the server

## Examples

### Get all books

```bash
curl -X GET http://localhost:8080/api/books
```

### Get a book by ID

```bash
curl -X GET http://localhost:8080/api/books/1
```

### Create a new book

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "description": "A novel about the American Dream",
    "isbn": "9780743273565",
    "publicationDate": "1925-04-10",
    "price": 12.99,
    "inStock": true
  }'
```

### Update a book

```bash
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "description": "Updated description",
    "isbn": "9780743273565",
    "publicationDate": "1925-04-10",
    "price": 14.99,
    "inStock": true
  }'
```

### Delete a book

```bash
curl -X DELETE http://localhost:8080/api/books/1
```
