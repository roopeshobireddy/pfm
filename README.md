# Personal Finance Manager

Production-oriented layered REST API built with Java 21, Spring Boot 3.5, Spring Data JPA, MySQL 8, Maven, Lombok, MapStruct, Bean Validation, Actuator, springdoc OpenAPI, JUnit 5 and Mockito.

## Run locally
1. Install JDK 21, Maven 3.9+, and MySQL 8.
2. Create the database (or allow the JDBC URL to create it):
   `CREATE DATABASE personal_finance CHARACTER SET utf8mb4;`
3. Set credentials with `DB_USERNAME` and `DB_PASSWORD`, or edit `application.properties`.
4. Run: `mvn clean test`
5. Start: `mvn spring-boot:run`

Swagger UI: http://localhost:8080/swagger-ui.html  
OpenAPI JSON: http://localhost:8080/v3/api-docs  
Health: http://localhost:8080/actuator/health

## Pagination and sorting
`GET /api/users?page=0&size=20&sort=createdAt,desc`  
`GET /api/expenses?page=0&size=20&sort=expenseDate,desc`  
Multiple sort fields are supported by repeating `sort`, e.g. `?sort=expenseDate,desc&sort=amount,asc`.

## Notes before production
- `ddl-auto=update` is convenient for local development. Use Flyway/Liquibase migrations and `ddl-auto=validate` in production.
- Supply database secrets through environment/secret manager; do not commit credentials.
- Add authentication/authorization (for example Spring Security + JWT/OAuth2), rate limiting, audit logging, and integration tests before exposing this API publicly.
- User deletion cascades to expenses. Confirm this behavior matches your retention requirements.
- Pagination endpoints currently rely on Spring Data `Page` serialization. Consider a stable custom page response contract if clients need long-term schema guarantees.
- API validation is enforced on DTOs; category values must exactly match the enum constants.
