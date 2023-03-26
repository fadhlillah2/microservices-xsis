# microservices-xsis

# Movie Management API
This is a Java Spring Boot application that provides a RESTful API for managing movies.

### Prerequisites
- Java 17
- Spring Boot 2.5.0
- MySQL 14

### Installation
To run the project, follow these steps:

1. Clone the repository
2. Create a MySQL database named movie_db
3. Update the database connection properties in application.properties
4. Run the project using ./mvnw spring-boot:run

### Data Model
The data model for this CRUD application consists of a single entity: MovieEntity. The Movie entity has the following properties:

- id: The unique identifier for the product
- name: The name of the product
- description: A brief description of the product
- price: The price of the product
- createdAt: The date and time when the product was created
- updatedAt: The date and time when the product was last updated

### API Endpoints
The following API endpoints are available:

GET /products: Returns a list of all products
GET /products/{id}: Returns the product with the specified ID
POST /products: Creates a new product
PUT /products/{id}: Updates the product with the specified ID
DELETE /products/{id}: Deletes the product with the specified ID
The request and response format for each endpoint can be found in the Swagger UI at http://localhost:8080/swagger-ui.html.

### Authentication and Authorization
This application does not require authentication or authorization to access the API endpoints.

### Testing
This application includes unit tests for the ProductController and ProductService classes. To run the tests, execute ./mvnw test.

### Deployment
To deploy this application to a production environment, you can create a Docker image and deploy it to a container orchestration platform like Kubernetes. An example Dockerfile and Kubernetes deployment manifest are provided in the deploy directory.

### Contributing
If you would like to contribute to this project, please create a pull request with your changes. Please ensure that all tests pass before submitting the pull request.

### License
This project is licensed under the MIT License. See the LICENSE file for details
