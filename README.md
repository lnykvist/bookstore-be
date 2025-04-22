
# Bookstore API

A Spring Boot REST API for managing a bookstore inventory.

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![License](https://img.shields.io/badge/License-Apache%202.0-orange)

## Overview

This application provides a RESTful API for managing books in a bookstore. It allows for creating, reading, updating, and deleting book records, as well as searching for books by various criteria.

## Features

- CRUD operations for book management
- Search books by title, author, or ISBN
- Interactive API documentation with Swagger UI
- Data validation
- Exception handling with appropriate HTTP status codes

## Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- Jakarta Persistence
- Jakarta Validation
- PostgreSQL/MySQL (database)
- Maven/Gradle (build tool)
- Swagger/OpenAPI (API documentation)

## API Documentation

The API is documented using Swagger/OpenAPI. Once the application is running, you can access the interactive documentation at:

```
http://localhost:8080/swagger-ui.html
```

The OpenAPI specification is available at:

```
http://localhost:8080/api-docs
```

## Prerequisites

- JDK 17 or higher
- Maven or Gradle
- PostgreSQL/MySQL database

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/bookstore-api.git
cd bookstore-api
```

### Configure Database

Edit the `application.properties` file to configure your database connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run

#### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

#### Using Gradle

```bash
gradle clean build
gradle bootRun
```

The application will start on port 8080 by default.

## API Endpoints

| Method | URL                        | Description                   | Request Body | Response Status |
|--------|----------------------------|-------------------------------|--------------|-----------------|
| GET    | /api/books                 | Get all books                 | -            | 200 OK          |
| GET    | /api/books/{id}            | Get book by ID                | -            | 200 OK, 404 Not Found |
| GET    | /api/books/isbn/{isbn}     | Get book by ISBN              | -            | 200 OK, 404 Not Found |
| GET    | /api/books/search/title    | Search books by title         | -            | 200 OK          |
| GET    | /api/books/search/author   | Search books by author        | -            | 200 OK          |
| POST   | /api/books                 | Create a new book             | Book object  | 201 Created, 400 Bad Request |
| PUT    | /api/books/{id}            | Update an existing book       | Book object  | 200 OK, 404 Not Found, 400 Bad Request |
| DELETE | /api/books/{id}            | Delete a book                 | -            | 204 No Content, 404 Not Found |

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
| price           | Double      | Book price                                       | Positive value, required     |
| inStock         | boolean     | Availability status                              | Default: true                |

### ISBN Format

The ISBN (International Standard Book Number) field must follow either the ISBN-10 or ISBN-13 format:

- **ISBN-10**: A 10-digit number (e.g., `0306406152`)
- **ISBN-13**: A 13-digit number, typically starting with 978 or 979 (e.g., `9780306406157`)

The API accepts ISBNs with or without hyphens (e.g., `978-0-306-40615-7`). The validation ensures that:
- The ISBN contains the correct number of digits (10 or 13)
- Hyphens may be included for readability but are not required
- The format follows the international ISBN standard

Example valid ISBNs:
- `0-306-40615-2`
- `978-3-16-148410-0`
- `9780306406157`

## Error Handling

The API uses standard HTTP status codes to indicate the success or failure of requests:

- 200 OK: The request was successful
- 201 Created: A new resource was successfully created
- 204 No Content: The request was successful but there is no content to return
- 400 Bad Request: The request was malformed or invalid
- 404 Not Found: The requested resource was not found
- 500 Internal Server Error: An unexpected error occurred on the server

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.
