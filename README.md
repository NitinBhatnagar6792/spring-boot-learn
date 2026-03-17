# Spring boot Learn

Simple Spring Boot project created using Spring Initializr.

- Purpose: Practice and study on below UDEMY course
- Course: https://www.udemy.com/course/java-programming-tutorial-for-beginners/
- Section 56: Getting Started with Spring Boot

## Run

./mvnw spring-boot:run


# Steps for creating the project in github
- using https://start.spring.io/ create a project and download it as spring-framework-learn.zip
- extract the project in some folder
- using git bash go to folder spring-framework-learn and run below git commands in git bash
- git init
- git add .
- git commit -m "Initial commit"
- now go to GitHub website and create the repo and run below command in git bash shell
- git remote add origin https://github.com/NitinBhatnagar6792/spring-boot-learn.git
- git push -u origin master

# How to access H2 DB url
- add 'spring.h2.console.enabled=true' in application.properties
- add 'spring.datasource.url=jdbc:h2:mem:testdb' application.properties
- in browser open url=http://localhost:9090/h2-console
- in the JDBC Url text box enter 'jdbc:h2:mem:testdb' and press Connect button



