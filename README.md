# Microservices XSIS - Movie Management API

## 📋 Project Overview

**Microservices XSIS** is a modern, scalable microservices-based backend system built with **Java Spring Boot 3.0.2** that provides a comprehensive **RESTful API for movie management**.

This project demonstrates enterprise-level software architecture patterns including:
- Clean layered architecture (Controller → Service → Repository → Entity)
- RESTful API design principles
- Spring Boot best practices
- Comprehensive unit testing with JUnit 5 and Mockito
- MySQL database persistence with Spring Data JPA

The project is designed as a foundation for a multi-service microservices architecture, with the Movie Service serving as the initial service. Future services can be seamlessly integrated into this scalable infrastructure.

---

## 🏗️ Project Architecture

### Layered Architecture Pattern

```
┌─────────────────────────────────────────────┐
│   REST Controller Layer                     │
│   (MovieController - HTTP Endpoints)        │
├─────────────────────────────────────────────┤
│   Service Layer                             │
│   (MovieService & Implementation)           │
│   (Business Logic & Orchestration)          │
├─────────────────────────────────────────────┤
│   Repository Layer                          │
│   (MovieRepository - Data Access)           │
│   (Spring Data JPA)                         │
├─────────────────────────────────────────────┤
│   Entity/Domain Layer                       │
│   (MovieEntity - Database Model)            │
│   (MySQL Persistence)                       │
└─────────────────────────────────────────────┘
```

### Project Structure

```
microservices-xsis/
├── movie-service/                           # Main microservice module
│   ├── src/main/java/com/beyonder/movieservice/
│   │   ├── MovieServiceApplication.java     # Spring Boot entry point
│   │   ├── controller/
│   │   │   └── MovieController.java         # REST API endpoints
│   │   ├── service/
│   │   │   ├── MovieService.java            # Service interface (contract)
│   │   │   └── implementation/
│   │   │       └── MovieServiceImpl.java     # Business logic implementation
│   │   ├── repository/
│   │   │   └── MovieRepository.java         # Data access layer (JPA)
│   │   ├── entity/
│   │   │   └── MovieEntity.java             # JPA entity & domain model
│   │   └── exception/
│   │       └── MovieNotFoundException.java  # Custom exception handling
│   ├── src/main/resources/
│   │   └── application.properties           # Configuration properties
│   ├── src/test/java/                       # Unit tests
│   │   ├── controller/
│   │   │   └── MovieControllerTest.java
│   │   └── service/implementation/
│   │       └── MovieServiceImplTest.java
│   ├── pom.xml                              # Maven configuration
│   ├── mvnw/mvnw.cmd                        # Maven wrapper scripts
│   └── movie-service-endpoints.http         # HTTP request examples
└── README.md                                # Project documentation
```

---

## 🛠️ Technology Stack

### Core Framework & Language
- **Java 17** - Latest LTS version for modern language features
- **Spring Boot 3.0.2** - Modern Spring framework for rapid application development
- **Spring Data JPA** - Object-Relational Mapping with Hibernate

### Database & Persistence
- **MySQL 8.0.28** - Relational database for data persistence
- **Hibernate ORM** - Database abstraction layer

### Development & Utilities
- **Lombok** - Reduces boilerplate code (getters, setters, constructors)
- **Jackson** - JSON serialization/deserialization
- **Spring Boot Actuator** - Production-ready monitoring and management

### Testing
- **JUnit 5** - Modern testing framework
- **Mockito** - Mocking framework for unit testing
- **MockMvc** - Testing Spring MVC layer

### Build & Development Tools
- **Maven 3.6+** - Build automation and dependency management
- **Maven Wrapper** - Ensures consistent Maven version across environments
- **Git** - Version control

---

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **MySQL 8.0** or higher
- **Maven 3.6+** (optional, Maven Wrapper included)
- **Git** for version control

### Installation & Setup

#### 1. Clone the Repository
```bash
git clone <repository-url>
cd microservices-xsis
```

#### 2. Configure Database

Create a MySQL database and user:

```sql
CREATE DATABASE dev;
CREATE USER 'root'@'localhost' IDENTIFIED BY '12345678';
GRANT ALL PRIVILEGES ON dev.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

**Note:** The default configuration uses `root/12345678` on `localhost:3306/dev`. Update these credentials in `movie-service/src/main/resources/application.properties` as needed.

#### 3. Run the Application

Using Maven Wrapper (recommended):
```bash
cd movie-service
./mvnw spring-boot:run
```

Or using Maven directly:
```bash
cd movie-service
mvn spring-boot:run
```

The API will be available at: **http://localhost:8082**

#### 4. Verify Installation

Check the health endpoint:
```bash
curl http://localhost:8082/actuator/health
```

You should receive:
```json
{
  "status": "UP"
}
```

---

## 📚 API Documentation

### Data Model: Movie Entity

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Unique identifier (auto-generated) |
| `title` | String | Movie title |
| `description` | String | Movie description/synopsis |
| `rating` | Double | Movie rating (e.g., 8.5) |
| `image` | String | Movie poster/image URL |
| `createdAt` | LocalDateTime | Creation timestamp (ISO 8601) |
| `updatedAt` | LocalDateTime | Last update timestamp (ISO 8601) |

### API Endpoints

#### 1. Get All Movies
```http
GET /Movies
```
**Response:** 200 OK
```json
[
  {
    "id": 1,
    "title": "Inception",
    "description": "A skilled thief who steals corporate secrets...",
    "rating": 8.8,
    "image": "https://example.com/inception.jpg",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  }
]
```

#### 2. Get Movie by ID
```http
GET /Movies/{id}
```
**Example:** `GET /Movies/1`
**Response:** 200 OK
```json
{
  "id": 1,
  "title": "Inception",
  "description": "A skilled thief who steals corporate secrets...",
  "rating": 8.8,
  "image": "https://example.com/inception.jpg",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

#### 3. Search Movies by Title
```http
GET /Movies/getByTitle?keyWord={searchTerm}
```
**Example:** `GET /Movies/getByTitle?keyWord=Inception`
**Response:** 200 OK - Returns all movies containing the search term

#### 4. Create New Movie
```http
POST /Movies
Content-Type: application/json

{
  "title": "Oppenheimer",
  "description": "The story of American scientist J. Robert Oppenheimer...",
  "rating": 8.0,
  "image": "https://example.com/oppenheimer.jpg"
}
```
**Response:** 201 Created
```json
{
  "id": 2,
  "title": "Oppenheimer",
  "description": "The story of American scientist J. Robert Oppenheimer...",
  "rating": 8.0,
  "image": "https://example.com/oppenheimer.jpg",
  "createdAt": "2024-01-15T11:45:00",
  "updatedAt": "2024-01-15T11:45:00"
}
```

#### 5. Update Movie
```http
PATCH /Movies/{id}
Content-Type: application/json

{
  "title": "Inception Updated",
  "rating": 8.9
}
```
**Example:** `PATCH /Movies/1`
**Response:** 200 OK - Updated movie entity

#### 6. Delete Movie
```http
DELETE /Movies/{id}
```
**Example:** `DELETE /Movies/1`
**Response:** 204 No Content

### HTTP Status Codes

| Status | Meaning |
|--------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 204 | No Content - Successful deletion |
| 400 | Bad Request - Invalid input |
| 404 | Not Found - Movie not found |
| 500 | Internal Server Error - Server error |

### Testing Endpoints

A comprehensive HTTP file with example requests is provided in `movie-service/movie-service-endpoints.http` for use with HTTP clients like Postman or IntelliJ HTTP Client.

---

## 🔧 Configuration

### Database Configuration
Located in: `movie-service/src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dev
spring.datasource.username=root
spring.datasource.password=12345678
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Server Configuration
```properties
server.port=8082
management.endpoints.web.exposure.include=*
```

### Application Properties Reference

| Property | Default | Description |
|----------|---------|-------------|
| `spring.datasource.url` | `jdbc:mysql://localhost:3306/dev` | Database connection URL |
| `spring.datasource.username` | `root` | Database user |
| `spring.datasource.password` | `12345678` | Database password |
| `spring.jpa.hibernate.ddl-auto` | `update` | Auto-create/update schema |
| `server.port` | `8082` | Server port |

---

## 🧪 Testing

### Running Tests

Execute all unit tests:
```bash
cd movie-service
./mvnw test
```

Or using Maven directly:
```bash
mvn test
```

### Test Coverage

The project includes comprehensive unit tests:

#### MovieControllerTest (`src/test/java/com/beyonder/movieservice/controller/`)
- Tests HTTP endpoints with MockMvc
- Validates request handling and response formatting
- Mocks the MovieService layer

**Test Cases:**
- `testGetMovies()` - Validates GET all endpoint
- `testGetMovieById()` - Validates GET by ID endpoint
- `testCreate()` - Validates POST endpoint
- `testUpdateMovie()` - Validates PATCH endpoint
- `testDeleteUser()` - Validates DELETE endpoint

#### MovieServiceImplTest (`src/test/java/com/beyonder/movieservice/service/implementation/`)
- Tests business logic implementation
- Mocks repository layer with Mockito

**Test Cases:**
- `getAllTest()` - Validates getAll service method
- `createTest()` - Validates create service method

### Test Framework Stack
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework
- **Spring Test** - Spring integration testing support

---

## 📝 Development Guidelines

### Code Structure Best Practices

1. **Separation of Concerns**
   - Controllers: Handle HTTP requests/responses
   - Services: Contain business logic
   - Repositories: Handle data access
   - Entities: Define domain models

2. **Naming Conventions**
   - Classes: `PascalCase`
   - Methods/variables: `camelCase`
   - Constants: `UPPER_CASE`
   - Interfaces: Prefix-less or suffix with `Interface`

3. **Lombok Usage**
   - `@Data` - Generates getters, setters, equals, hashCode, toString
   - `@RequiredArgsConstructor` - Generates constructor for final fields
   - `@Slf4j` - Generates logger field

### Adding a New Endpoint

1. Define the endpoint in `MovieController`
2. Create the service method in `MovieService` interface
3. Implement in `MovieServiceImpl`
4. Add repository method if needed in `MovieRepository`
5. Write unit tests in `MovieControllerTest` and `MovieServiceImplTest`

---

## 🔐 Authentication & Authorization

Currently, this API does **not require authentication or authorization**. All endpoints are publicly accessible.

**Future Enhancement:** Consider implementing:
- JWT (JSON Web Token) authentication
- Role-based access control (RBAC)
- API key validation

---

## 🐳 Deployment

### Docker (In Development)

Docker support is currently under development. Future releases will include:
- Dockerfile for containerization
- Docker Compose for local development with MySQL
- Container orchestration examples (Kubernetes)

---

## 🤝 Contributing

We welcome contributions! To contribute:

1. **Fork the repository**
2. **Create a feature branch:**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes** and ensure tests pass:
   ```bash
   ./mvnw test
   ```
4. **Commit with clear messages:**
   ```bash
   git commit -m "Add feature: description"
   ```
5. **Push to the branch:**
   ```bash
   git push origin feature/your-feature-name
   ```
6. **Submit a Pull Request** with a detailed description

### Code Quality Standards
- All tests must pass: `./mvnw test`
- Follow the existing code style and conventions
- Add unit tests for new features
- Update documentation as needed

---

## 📄 License

This project is licensed under the **MIT License**. See the LICENSE file for details.

---

## 📞 Support & Resources

- **Documentation:** See this README and inline code comments
- **API Testing:** Use `movie-service/movie-service-endpoints.http`
- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Spring Data JPA:** https://spring.io/projects/spring-data-jpa

---

## 🗺️ Roadmap

- [x] Basic CRUD API endpoints
- [x] Search functionality (by title)
- [x] Unit tests
- [ ] Docker containerization
- [ ] Kubernetes deployment examples
- [ ] Additional microservices (User Service, Review Service, etc.)
- [ ] Authentication & Authorization (JWT)
- [ ] API documentation (Swagger/OpenAPI)
- [ ] CI/CD pipeline integration
- [ ] Performance optimization & caching

---

**Last Updated:** January 2024
**Current Version:** 0.0.1-SNAPSHOT
