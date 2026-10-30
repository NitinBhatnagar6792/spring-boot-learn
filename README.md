# 1. Spring boot Learn

Simple Spring Boot project created using Spring Initializr.

- Purpose: Practice and study on below UDEMY course
- Course: https://www.udemy.com/course/java-programming-tutorial-for-beginners/
- Section 56: Getting Started with Spring Boot

## 2. Run

./mvnw spring-boot:run


# 3. Steps for creating the project in github
- using https://start.spring.io/ create a project and download it as spring-framework-learn.zip
- extract the project in some folder
- using git bash go to folder spring-framework-learn and run below git commands in git bash
- git init
- git add .
- git commit -m "Initial commit"
- now go to GitHub website and create the repo and run below command in git bash shell
- git remote add origin https://github.com/NitinBhatnagar6792/spring-boot-learn.git
- git push -u origin master

# 4. Steps for configuring code review using [ChatGPT](https://chatgpt.com/settings/code-review)

- In ChatGPT go to [plugins](https://chatgpt.com/plugins?search=github) and search for GitHub
- Install the GitHub plugin while doing so it will ask for github credentials and repos for which you want to give access to chatgpt
- Now create a PR and add a comment starting with '@codex review' to trigger the code review

```
@codex review: Do a very basic code review
@codex review: Please review the code written in file src/main/java/com/nb/spring/boot/learn/services/CourseService.java as part of this PR, suggest some improvement however do not make any change to the code
```

# 5. Steps for configuring in-memory H2 DB.

## 5.a. in pom.xml add below dependencies

```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-h2console</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## 5.b. In application.properties add below properties for enabling H2 DB

```
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
```

## 5.c. The username and password configured under spring.datasource are used to connect to the H2 Console

```
spring.datasource.username=sa
spring.datasource.password=
```

## 5.d. About H2-Console

- you can access the H2 Console at **http://localhost:{server.port}/{spring.h2.console.path}** i.e. **http://localhost:9090/h2-console**
- in the JDBC Url text box enter **jdbc:h2:mem:testdb** i.e. based on the value of property **spring.datasource.url**
- The **username** and **password** configured under **spring.datasource** are used to connect to the H2 Console i.e. based on the value of property **spring.datasource.username** and **spring.datasource.password**
- Press **Test Connection** button to test the connection and **Connect** button to login into H2 Console

## 5.e. Configure Automatic execution of SQL scripts during application startup.

### 5.e.i. In application.properties add below property.

```
spring.sql.init.mode=always
```

### 5.e.ii. By default, Spring Boot looks for schema.sql and data.sql under /src/main/resources/ and execute them

#### 5.e.ii.1. schema.sql : Used for executes DDL statements (CREATE TABLE, ALTER TABLE, etc.)

```
CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);
```

#### 5.e.ii.2. data.sql : Used for executes DML statements (INSERT, UPDATE, DELETE, etc.)

```
INSERT INTO courses (name, author) VALUES ('Learn AWS', 'Jack');
INSERT INTO courses (name, author) VALUES ('Learn Java', 'Bruce');
INSERT INTO courses (name, author) VALUES ('Learn Oracle', 'Caterine');
```



