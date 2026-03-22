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

- Step 3 - Build Docker Image from windows command prompt
```
cd ..\spring-boot-learn
mvn clean package
docker build -t spring-boot-learn-h2 .
	
```

- Step 4 - Verify Image
```
docker images

```

- Step 5 - Run Container
```
docker run -p 9090:9090 spring-boot-learn-h2
```

- Step 6 - test the app
```
GET: http://localhost:9090/health-check
GET: http://localhost:9090/courses
```


# Summery of commands 
- open windows commond prompt and go to source code root folder
- cd ...\spring-boot-learn
- mvn --version
- mvn clean install
- docker build -t spring-boot-learn-h2 .
- docker --images
- docker run -d -p 9090:9090 spring-boot-learn-h2
- test the application by hitting http://localhost:9090/health-check in browser
