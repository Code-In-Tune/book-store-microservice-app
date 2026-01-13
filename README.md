# Book Store
## What it is
Book Store microservice in Spring Boot with PostgreSQL
## Tech Stack
Technologies used:
* Spring Boot
* Jakarta Validator
* JPA
* Spring Data JPA
* Criteria API (for filtered results)
* Mapstruct
* Docker (Various Docker compose for quick startup)
* Logback
* OAuth2.0 and OIDC
* Keycloak
* Spring Security
* I18N (for internationalization of error message)
* PostgreSQL
* OpenApi/Swagger (for documentation of endpoints)
* Junit 5
* MockMvc
* Mockito
* Testcontainers (for integration testing)
* Webclient (only to wrap authentication call to keycloak)
## Features
The following features are being provided by the microservice:
* Add a book (POST)
* Update a book (PUT)
* Update book quantity (PATCH)
* Delete a book (DELETE)
* Search book by Id (GET)
* Get all books paginated (GET)
* Get all books according to a certain filter (POST)
* Add a book sale (POST)
* List all sales, paginated (GET)
## Quick start
In order to start the project, first run the file docker-compose-db.yml, then docker-compose-keycloak.yml.
Then run the application. In order to access the endpoints you have to make a request to keycloak with the following credentials and get the access token:
* username: admin; password: admin; (This grants read-view access)
* username: user; password: user; (This grants only view privileges)
For convenience, I've added a /auth endpoint which can be used to authenticate quickly once keycloak is started.
You can use /swagger-ui.html and go the auth tab and make the call from there.
The application runs on 8081, while keycloak run on 8080, localhost.
## Testing
I've provided both unit tests and integration tests whenever needed.
Here i provide also data to test the add book endpoint:
1. Title: Oliver Twist
2. Author: Charles Dickens
3. Quantity: 1
4. Price: 11.99
5. Publisher: Zenith Velvet Ink Publishing
6. ISBN: 9791070126875

Enjoy
