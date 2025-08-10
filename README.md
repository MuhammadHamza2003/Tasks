# REST API - Book Management System

## Project Overview

This is a Spring Boot REST API application for book management. The application provides a complete CRUD (Create, Read, Update, Delete) interface for managing books in a MongoDB database. It allows users to store and manage book information including ISBN, title, author, price, and publication date.

The API is built using modern Spring Boot architecture with clean separation of concerns through controller, service, and repository layers. It supports JSON data exchange and provides RESTful endpoints for all book management operations.

## Technologies Used

- **Java 21** - Programming language
- **Spring Boot 3.5.4** - Application framework
- **Spring Web** - For building REST APIs
- **Spring Data MongoDB** - For MongoDB integration and data access
- **MongoDB** - NoSQL database for data storage
- **OpenCSV 5.12.0** - For CSV file processing capabilities
- **Maven** - Build tool and dependency management
- **Spring Boot Test** - For unit and integration testing

## How to Run Locally

### Prerequisites

Before running the application, ensure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+**
- **MongoDB** (running on localhost:27017)
- **Git** (for cloning the repository)

### Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/MuhammadHamza2003/Tasks.git
   cd Tasks
   ```

2. **Ensure MongoDB is running:**

   - Install MongoDB if not already installed
   - Start MongoDB service on localhost:27017
   - The application will automatically create a database named "books"

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

5. **Alternative run method:**
   ```bash
   java -jar target/Rest-API-0.0.1-SNAPSHOT.jar
   ```

The application will start on `http://localhost:8080`

### Configuration

The application uses the following default configuration (in `application.properties`):

- **Port:** 8080
- **MongoDB Host:** localhost
- **MongoDB Port:** 27017
- **Database Name:** books

## List of Endpoints with Sample Inputs/Outputs

### Base URL: `http://localhost:8080/books`

### 1. Get All Books

- **Method:** `GET`
- **Endpoint:** `/books`
- **Description:** Retrieve all books from the database

**Sample Response:**

```json
[
  {
    "isbn": "978-0134685991",
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "price": 45,
    "publishedDate": "2018-01-06T00:00:00.000+00:00"
  },
  {
    "isbn": "978-0596009205",
    "title": "Head First Design Patterns",
    "author": "Eric Freeman",
    "price": 35,
    "publishedDate": "2004-10-25T00:00:00.000+00:00"
  }
]
```

### 2. Create a New Book

- **Method:** `POST`
- **Endpoint:** `/books`
- **Description:** Add a new book to the database

**Sample Request Body:**

```json
{
  "isbn": "978-0134685991",
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "price": 45,
  "publishedDate": "2018-01-06T00:00:00.000+00:00"
}
```

**Sample Response:**

```json
"Book created successfully with ISBN: 978-0134685991"
```

### 3. Get Book by ID

- **Method:** `GET`
- **Endpoint:** `/books/id:{id}`
- **Description:** Retrieve a specific book by its ISBN

**Sample Request:**

```
GET /books/id:978-0134685991
```

**Sample Response:**

```json
{
  "isbn": "978-0134685991",
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "price": 45,
  "publishedDate": "2018-01-06T00:00:00.000+00:00"
}
```

### 4. Update Book by ID

- **Method:** `PUT`
- **Endpoint:** `/books/id:{id}`
- **Description:** Update an existing book's information

**Sample Request:**

```
PUT /books/id:978-0134685991
```

**Sample Request Body:**

```json
{
  "isbn": "978-0134685991",
  "title": "Effective Java - Third Edition",
  "author": "Joshua Bloch",
  "price": 50,
  "publishedDate": "2018-01-06T00:00:00.000+00:00"
}
```

**Sample Response:**

```json
true
```

### 5. Delete Book by ID

- **Method:** `DELETE`
- **Endpoint:** `/books/id:{id}`
- **Description:** Remove a book from the database

**Sample Request:**

```
DELETE /books/id:978-0134685991
```

**Sample Response:**

```json
true
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/Rest/API/
│   │   ├── RestApiApplication.java      # Main Spring Boot application class
│   │   ├── config/
│   │   │   └── WebConfig.java          # Web configuration
│   │   ├── controller/
│   │   │   └── RestAPIController.java  # REST endpoints
│   │   ├── entity/
│   │   │   └── Entity.java             # Book entity model
│   │   ├── repository/
│   │   │   └── APIRepository.java      # Data access layer
│   │   └── service/
│   │       └── APIservice.java         # Business logic layer
│   └── resources/
│       └── application.properties      # Application configuration
└── test/
    └── java/com/example/Rest/API/
        └── RestApiApplicationTests.java # Test classes
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).
