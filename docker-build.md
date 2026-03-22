### Steps to build docker image

- Step1 - In pom.xml add Spring Boot Maven Plugin
```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

- Step2 - Create Dockerfile (Modern Java 25 Compatible)
```
# Use lightweight OpenJDK image (Java 25 compatible)
FROM eclipse-temurin:25-jdk-jammy

# Set working directory
WORKDIR /app

# make container lisit at a port 9090
EXPOSE 9090

# Copy jar from target folder
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

- Step 3 - update application properties

```
spring.datasource.hikari.initializationFailTimeout=60000
spring.datasource.url=jdbc:mysql://mysql:3306/spring-boot-learn?useSSL=false&serverTimezone=UTC

```

- Step 4 - Build Docker Image from windows command prompt
```
cd ..\spring-boot-learn
mvn clean package -DskipTests
docker build -t spring-boot-learn-mysql .
	
```

- Step 5 - Verify Image
```
docker images

```

- Step 6 - Start MqSql docker image
```
docker run -d --name mysql --network spring-net -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_USER=springboot-user -e MYSQL_PASSWORD=dummyboot -e MYSQL_DATABASE=spring-boot-learn -p 3306:3306 mysql:5.7
```

- Step 6 - Run Container
```
docker run -d --name spring-app  --network spring-net  -p 9090:9090  spring-boot-learn-mysql
```

- Step 6 - test the app
```
GET: http://localhost:9090/health-check
```


# Summery of commands 
- open windows commond prompt and go to source code root folder
- cd ...\spring-boot-learn
- mvn --version
- mvn clean package -DskipTests
- docker build -t spring-boot-learn-mysql .
- docker images
- docker network create spring-net
- docker run -d --name mysql --network spring-net -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_USER=springboot-user -e MYSQL_PASSWORD=dummyboot -e MYSQL_DATABASE=spring-boot-learn -p 3306:3306 mysql:5.7
- docker run -d --name spring-app  --network spring-net  -p 9090:9090  spring-boot-learn-mysql
- test the application by hitting http://localhost:9090/health-check in browser
