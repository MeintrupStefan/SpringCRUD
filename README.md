# SpringCRUD
A collection of spring CRUD operations for later reference.

This project uses Java 17 & Spring 2.7 and was initialized with https://start.spring.io/.

### Other dependencies
- Webserver: Tomcat
- Database: MySQL
- Documentation: Swagger (springdoc-openapi)
- Logging: SLF4J (Abstraction) & Log4j (Specific login implementation)
- Annotations: Lombok


### All endpoints with doc
- Swagger Ui doc: http://localhost:8080/swagger-ui.html

### Road Map
- [x] Init Spring Boot proj
- [x] Add Swagger support with openapi
- [x] Add Logging with Lombok, SLF4j & Log4j
- [x] Create some rest & view endpoints to start
- [x] Create Unit test (JUnit5)
- [ ] Add aspect for endpoint error handling
- [ ] Write integration test to test error handling aspect for an endpoint
- [ ] Implement authentication
- [ ] Implement an interchangeable db connection that (first) does not require a running server
- [ ] Implement Repository to store user data in db
- [ ] Implement endpoints that store user data in db