# SpringCRUD
A collection of spring CRUD operations for later reference.

This project uses Java 17, Spring 2.7, Maven & Swagger and was initialized with https://start.spring.io/.

### All endpoints with doc
- Swagger Ui doc: http://localhost:8080/swagger-ui.html

### Run project
- Import repo
- Build maven project
- Run SpringcrudApplication.java (located in src.main.java.com.meintrup.springcrud.SpringcrudApplication)

### Dependencies
- Java 17
- Maven
- Spring 2.7
- Spring Boot
- Documentation: Swagger (springdoc-openapi)
- Webserver: Tomcat
- Logging: SLF4J (Abstraction) & Log4j (Specific login implementation)
- Annotations: Lombok
- Database:


### Road Map
- [x] Init Spring Boot proj
- [x] Add Swagger support with openapi
- [x] Add Logging with Lombok, SLF4j & Log4j
- [x] Create some rest & view endpoints to start
- [x] Create Unit test (JUnit5)
- [x] Add aspect for endpoint error handling
- [x] Write integration test to test error handling aspect for an endpoint, especially 400 on missing attributes & missing endpoint
- [x] Implement an interchangeable db connection that (first) does not require a running db server
- [x] Implement Repository to store user data in db
- [x] Implement endpoints that store user data in db
- [x] Implement post endpoint
- [ ] Spring start here keep reading from 237
- [ ] Implement delete endpoint
- [ ] Implement patch endpoint
- [ ] Implement MySql DB Connection And DB table
- [x] Implement authentication
- [ ] Implement websocket
- [ ] Implement communication between two micro services
- [ ] CI/CD
- [ ] Implement stream
- [ ] Check all TODOs
- [ ] Deploy in docker container