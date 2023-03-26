# microservices-xsis

# Movie Management API
This is a Java Spring Boot application that provides a RESTful API for managing movies.

### Prerequisites
- Java 17
- Spring Boot 3.0.2
- MySQL 14

### Installation
To run the project, follow these steps:

1. Clone the repository
2. Create a MySQL database named movie_db
3. Update the database connection properties in application.properties
4. Run the project using ./mvnw spring-boot:run

### Data Model
The data model for this CRUD application consists of a single entity: MovieEntity. The Movie entity has the following properties:

- id: The unique identifier for the movie
- title: The title of the movie
- description: A brief description of the movie
- rating: The rating of the movie
- createdAt: The date and time when the movie was created
- updatedAt: The date and time when the movie was last updated

### API Endpoints
The following API endpoints are available:

- GET /Movies: Returns a list of all movies
- GET /Movies/{id}: Returns the movie with the specified ID
- POST /Movies: Creates a new movie
- PATCH /Movies/{id}: Updates the movie with the specified ID
- DELETE /Movies/{id}: Deletes the movie with the specified ID


### Authentication and Authorization
This application does not require authentication or authorization to access the API endpoints.

### Testing
This application includes unit tests for the MovieController and MovieServiceImpl classes. To run the tests, execute ./mvnw test.

### Deployment
To deploy this application to a production environment, you can create a Docker image and deploy it to a container orchestration platform like Kubernetes. An example Dockerfile and Kubernetes deployment manifest are provided in the deploy directory.

### Contributing
If you would like to contribute to this project, please create a pull request with your changes. Please ensure that all tests pass before submitting the pull request.

### License
This project is licensed under the MIT License. See the LICENSE file for details
