# REST API - Book Management System

## Project Overview

This is a **Spring Boot REST API application** for comprehensive book management. The application provides a complete CRUD (Create, Read, Update, Delete) interface for managing books in a MongoDB database. It allows users to store and manage book information including ISBN, title, author, price, and publication date.

The API is built using modern Spring Boot architecture with clean separation of concerns through controller, service, and repository layers. It supports JSON data exchange, proper HTTP status codes, error handling, and provides RESTful endpoints for all book management operations.

### Key Features:
- ✅ **Complete CRUD Operations** - Create, Read, Update, Delete books
- ✅ **RESTful API Design** - Following REST conventions and best practices
- ✅ **MongoDB Integration** - NoSQL database for flexible data storage
- ✅ **Error Handling** - Comprehensive error handling with proper HTTP status codes
- ✅ **Input Validation** - Validates required fields and checks for duplicates
- ✅ **CORS Support** - Cross-Origin Resource Sharing enabled
- ✅ **Clean Architecture** - Layered architecture with separation of concerns

## Technologies Used

- **Java 21** - Latest LTS version of Java programming language
- **Spring Boot 3.5.4** - Modern Java application framework
- **Spring Web** - For building REST APIs and web services
- **Spring Data MongoDB** - For MongoDB integration and data access layer
- **MongoDB** - NoSQL document database for data storage
- **OpenCSV 5.12.0** - For CSV file processing capabilities (future enhancements)
- **Maven** - Build automation and dependency management tool
- **Spring Boot Test** - For comprehensive unit and integration testing

## How to Run Locally

### Prerequisites

Before running the application, ensure you have the following installed on your system:

- **Java 21** or higher ([Download here](https://adoptium.net/temurin/releases/))
- **Maven 3.6+** ([Download here](https://maven.apache.org/download.cgi))
- **MongoDB** - Running on localhost:27017 ([Download here](https://www.mongodb.com/try/download/community))
- **Git** - For cloning the repository ([Download here](https://git-scm.com/downloads))

### Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MuhammadHamza2003/Tasks.git
   cd Tasks
   ```

2. **Ensure MongoDB is running:**
   ```bash
   # On Windows (if MongoDB installed as service)
   net start MongoDB
   
   # On macOS/Linux
   sudo systemctl start mongod
   
   # Or run manually
   mongod --dbpath /path/to/your/data/directory
   ```
   - The application will automatically create a database named "books"
   - Default connection: `localhost:27017`

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   # Option 1: Using Maven
   mvn spring-boot:run
   
   # Option 2: Using Java directly
   java -jar target/Rest-API-0.0.1-SNAPSHOT.jar
   ```

5. **Verify the application is running:**
   - Open your browser and navigate to `http://localhost:8080/books`
   - You should see an empty array `[]` or existing books if any

The application will start on `http://localhost:8080`

### Configuration

The application uses the following default configuration (in `application.properties`):
```properties
spring.application.name=Rest-API
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=books
```

To change these settings, modify the `src/main/resources/application.properties` file.

## List of Endpoints with Sample Inputs/Outputs

### Base URL: `http://localhost:8080/books`

### 1. 📚 Get All Books
- **Method:** `GET`
- **Endpoint:** `/books`
- **Description:** Retrieve all books from the database
- **Status Codes:** 
  - `200 OK` - Books found
  - `204 No Content` - No books in database
  - `500 Internal Server Error` - Server error

**Sample Request:**
```bash
curl -X GET http://localhost:8080/books
```

**Sample Response (200 OK):**
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

### 2. ➕ Create a New Book
- **Method:** `POST`
- **Endpoint:** `/books`
- **Description:** Add a new book to the database
- **Status Codes:**
  - `201 Created` - Book created successfully
  - `400 Bad Request` - Invalid input data
  - `409 Conflict` - Book with ISBN already exists
  - `500 Internal Server Error` - Server error

**Sample Request:**
```bash
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{
    "isbn": "978-0134685991",
    "title": "Effective Java",
    "author": "Joshua Bloch", 
    "price": 45,
    "publishedDate": "2018-01-06T00:00:00.000+00:00"
  }'
```

**Sample Response (201 Created):**
```json
"Book created successfully with ISBN: 978-0134685991"
```

**Error Response (409 Conflict):**
```json
"Error: Book with ISBN 978-0134685991 already exists"
```

### 3. 🔍 Get Book by ISBN
- **Method:** `GET`
- **Endpoint:** `/books/id:{isbn}`
- **Description:** Retrieve a specific book by its ISBN
- **Status Codes:**
  - `200 OK` - Book found
  - `404 Not Found` - Book not found
  - `500 Internal Server Error` - Server error

**Sample Request:**
```bash
curl -X GET http://localhost:8080/books/id:978-0134685991
```

**Sample Response (200 OK):**
```json
{
  "isbn": "978-0134685991",
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "price": 45,
  "publishedDate": "2018-01-06T00:00:00.000+00:00"
}
```

### 4. ✏️ Update Book by ISBN
- **Method:** `PUT`
- **Endpoint:** `/books/id:{isbn}`
- **Description:** Update an existing book's information
- **Status Codes:**
  - `200 OK` - Book updated successfully
  - `404 Not Found` - Book not found
  - `500 Internal Server Error` - Server error

**Sample Request:**
```bash
curl -X PUT http://localhost:8080/books/id:978-0134685991 \
  -H "Content-Type: application/json" \
  -d '{
    "isbn": "978-0134685991",
    "title": "Effective Java - Third Edition",
    "author": "Joshua Bloch",
    "price": 50,
    "publishedDate": "2018-01-06T00:00:00.000+00:00"
  }'
```

**Sample Response (200 OK):**
```json
"Book updated successfully with ISBN: 978-0134685991"
```

### 5. 🗑️ Delete Book by ISBN
- **Method:** `DELETE`
- **Endpoint:** `/books/id:{isbn}`
- **Description:** Remove a book from the database
- **Status Codes:**
  - `200 OK` - Book deleted successfully
  - `404 Not Found` - Book not found
  - `500 Internal Server Error` - Server error

**Sample Request:**
```bash
curl -X DELETE http://localhost:8080/books/id:978-0134685991
```

**Sample Response (200 OK):**
```json
"Book deleted successfully with ISBN: 978-0134685991"
```

## 🏗️ Project Structure

```
Rest-API/
├── src/
│   ├── main/
│   │   ├── java/com/example/Rest/API/
│   │   │   ├── RestApiApplication.java      # 🚀 Main Spring Boot application class
│   │   │   ├── config/
│   │   │   │   └── WebConfig.java          # 🌐 CORS and web configuration
│   │   │   ├── controller/
│   │   │   │   └── RestAPIController.java  # 🎮 REST API endpoints
│   │   │   ├── entity/
│   │   │   │   └── Entity.java             # 📝 Book entity/model
│   │   │   ├── repository/
│   │   │   │   └── APIRepository.java      # 💾 Data access layer
│   │   │   └── service/
│   │   │       └── APIservice.java         # 🔧 Business logic layer
│   │   └── resources/
│   │       └── application.properties      # ⚙️ Application configuration
│   └── test/
│       └── java/com/example/Rest/API/
│           └── RestApiApplicationTests.java # 🧪 Test classes
├── target/                                  # 📦 Compiled classes and JAR
├── .mvn/                                   # 🔧 Maven wrapper files
├── pom.xml                                 # 📋 Maven dependencies and build config
├── .gitignore                              # 🚫 Git ignore patterns
└── README.md                               # 📖 This documentation
```

## 🧪 Testing the API

You can test the API using various tools:

### Using cURL (Command Line)
```bash
# Get all books
curl -X GET http://localhost:8080/books

# Create a book  
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"isbn":"123-456","title":"Test Book","author":"Test Author","price":25}'

# Get specific book
curl -X GET http://localhost:8080/books/id:123-456

# Update book
curl -X PUT http://localhost:8080/books/id:123-456 \
  -H "Content-Type: application/json" \
  -d '{"isbn":"123-456","title":"Updated Book","author":"Test Author","price":30}'

# Delete book
curl -X DELETE http://localhost:8080/books/id:123-456
```

### Using Postman
1. Import the endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. Test each endpoint with sample data

### Using Browser (for GET requests)
- Navigate to `http://localhost:8080/books` to see all books
- Navigate to `http://localhost:8080/books/id:SOME_ISBN` to see a specific book

## 🚀 Development Workflow

### Making Changes
1. **Create a feature branch:**
   ```bash
   git checkout -b feature/new-feature
   ```

2. **Make your changes and test:**
   ```bash
   mvn clean test
   mvn spring-boot:run
   ```

3. **Commit and push:**
   ```bash
   git add .
   git commit -m "Add new feature"
   git push origin feature/new-feature
   ```

4. **Create a Pull Request**

### Building for Production
```bash
# Clean and build
mvn clean package

# Run the JAR file
java -jar target/Rest-API-0.0.1-SNAPSHOT.jar
```

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Make your changes and add tests**
4. **Ensure all tests pass** (`mvn test`)
5. **Commit your changes** (`git commit -am 'Add amazing feature'`)
6. **Push to the branch** (`git push origin feature/amazing-feature`)
7. **Create a Pull Request**

### Code Style Guidelines
- Follow Java naming conventions
- Add JavaDoc comments for public methods
- Include unit tests for new features
- Maintain the existing project structure

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 📞 Support

If you encounter any issues or have questions:
1. Check the [Issues](https://github.com/MuhammadHamza2003/Tasks/issues) page
2. Create a new issue if your problem isn't already reported
3. Provide detailed information about the problem and your environment

---

**Happy Coding! 🚀**
