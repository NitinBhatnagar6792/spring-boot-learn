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

### 5.e.ii. By default, Spring Boot looks for schema.sql and data.sql under /src/main/resources/ and execute them at the application startup

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

# 6. Notes

- H2 DB related configuration properties have been moved to h2 profile by creating application-h2.properties so that we can have seperate profile for mysql

- File postman-curls.md will be marked as deprecated since now Postman collection 'spring-boot-learn.postman_collection.json' have been added to this repo

- Refer file on-windows11-install-ubuntu-and-docker.txt on how to install Ubuntu and Docker on windows 11

# 7 Launch MySQL 1st time using Docker in ubuntu shell

```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=springboot-user --env MYSQL_PASSWORD=dummyboot --env MYSQL_DATABASE=spring-boot-learn --name mysql --publish 3306:3306 mysql:5.7
```

later the same container can be started by running command 'docker start mysql'
    
## 7.a Check the Tables in MYSQL Using Docker

Run below command to execute just one SQL

```
docker exec -it mysql mysql -u springboot-user -pdummyboot spring-boot-learn -e "SELECT * FROM books;"
```

Run below command to open an interactive MySQL session

```
docker exec -it mysql mysql -u springboot-user -p
```

then in the shell we can list the database and select one database to execute SQL against it

```
mysql> show databases;
mysql> use spring-boot-learn;
mysql> select * from books;
mysql> exit;
```

## 7.b. few useful docker commands

```
docker version
docker ps
docker ps -a
docker images
docker image ls
docker run hello-world
docker container ls
docker start mysql
docker logs mysql
docker rm mysql
docker container stop ID
```

# 8 For building docker image out of this spring boot application refer below files

- Dockerfile
- steps-to-build-docker-image-for-spring-boot-application.md


# 9 Using docker-compose for starting multiple docker images

## 9.a refer docker-compose.yml file present in the project

- Important section of docker-compose.yml

```
services                - Is the root section where we define the containers that make up our application, in this case we have two services: mysql and spring-app
mysql, spring-app       - Service name; also the hostname used by other containers
image                   - Docker image used to create the container
environment             - Environment variables used by that service
ports                   - Maps host port to container port (format is HOST_PORT:CONTAINER_PORT)
volumes                 - Persists files outside the container's writable layer. This ensures that MySQL data survives container removal and recreation.
depends_on              - This tells Docker Compose to start the MySQL service before starting the Spring Boot service. depends_on by itself does not guarantee that MySQL is ready to accept connections. It only establishes startup ordering.
```

## 9.b important docker compose commands

- Start and Stop

```
docker compose up -d                    - Create/start the environment in background
docker compose stop                     - Stop containers without removing them
docker compose start                    - Start previously stopped containers
docker compose restart                  - Restart containers
docker compose down                     - Stop and remove containers and network
```

- Monitor

```
docker compose ps                       - List project containers
docker compose logs                     - View all logs
docker compose logs -f spring-app       - Follow Spring Boot logs
docker compose logs --tail=100 mysql    - Last 100 MySQL log lines
```

- Troubleshoot and manage

```
docker compose config                   - Validate Compose configuration
docker compose exec mysql bash          - Open a shell inside MySQL container
```

- In the bash shell for mysql we can connect to database and run SQL commands as shown below

```
bash-4.2# mysql -u springboot-user -pdummyboot
mysql> SHOW DATABASES;
mysql> USE spring-boot-learn;
mysql> Select * from books;
mysql> exit
bash-4.2# exit
```

- Run below command to execute just one SQL in bash shell

```
docker compose exec mysql mysql -u springboot-user -pdummyboot -e "USE spring-boot-learn; SELECT * FROM books;"
```
