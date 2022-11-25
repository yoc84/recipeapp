
## Recipe SpringBoot App (recipeapp)
Production ready maven based Spring Boot Java application for managing Recipes.

## Description
The purpose of this project is to provide CRUD APIs to manage the recipes. It allows adding, updating, removing and fetching recipes. Also, a search api to filter recipes based on one or more criteria.

## Architecture Considerations
- Using Spring Boot Java we can develop stand-alone, production-grade applications with ease and fast. It provides an easier and faster way to set up, configure, and run.
- MongoDB NoSQL database is used in the application as the document model because its easily scalable, allows us to structure data any way the application needs, and breaks down silos. Also, it offers rich set of APIs and provides finer control over queries.

## Technology

- **Spring Boot**     - Server side framework
- **SpringDataMongoDB**     - provides integration with the MongoDB document database
- **Swagger**         - In-built swagger2 documentation support
- **Junit**           - Unit testing framework
- **MongoDB**         - NoSQL database embedded version for unit tests & works with local version too
- **Actuator**        - Spring Boot actuator with default endpoints included.

## Setup
- Java 11
- Maven 3.x
- Preferred IDE

## Running the application locally
The Recipe application (recipeapp) can be started using IDE after importing the project. Also, can use the following command to run:

````
mvn spring-boot:run
````
The application will get started on default port 8080. 

## Swagger Documentation
Swagger documentation is in-built in this application. All the APIs can be tested using the URL. When application is started locally, it can be accessed using URL:
````
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
````

## Unit test cases
There are multiple unit test cases written to cover the different components of the application

````
mvn clean test -Dtest=
````

## Integration test cases
There are multiple integration test cases written to cover the different components of the application

````
mvn clean test -Dtest=
````

**Contributors:**
[Yogesh Chintha](https://www.linkedin.com/in/yogeshchintha/)



