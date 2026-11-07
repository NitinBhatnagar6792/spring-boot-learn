## Steps to build docker image

### Step 1 - In pom.xml add Spring Boot Maven Plugin

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

### Step 2 - Create Dockerfile (Modern Java 25 Compatible)

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

### Step 3 - Build Docker Image from windows command prompt

```
cd ..\spring-boot-learn
mvn clean package -DskipTests
docker build -t spring-boot-learn .
```

### Step 4 - Verify Image

```
docker images

```

### Step 5 - Run Container using H2 database

```
docker run -d -p 9090:9090 -e SPRING_PROFILES_ACTIVE=h2 --name spring-boot-learn-h2 spring-boot-learn
```

List of commands run in windows command prompt Start and Stop

```
..\spring-boot-learn>docker run -d -p 9090:9090 -e SPRING_PROFILES_ACTIVE=h2 --name spring-boot-learn-h2 spring-boot-learn
43d4228f09ab36b1732b176409af8e30d3a99613e3d715d05c3492081a30f74d

..\spring-boot-learn>docker ps
CONTAINER ID   IMAGE               COMMAND                  CREATED         STATUS         PORTS                                         NAMES
43d4228f09ab   spring-boot-learn   "java -jar /app/app.…"   7 seconds ago   Up 7 seconds   0.0.0.0:9090->9090/tcp, [::]:9090->9090/tcp   spring-boot-learn-h2

..\spring-boot-learn>docker stop spring-boot-learn-h2
spring-boot-learn-h2
```

### Step 6 - Run Container using MYSQL database running in docker

#### Create network (only once)

```
docker network create spring-network
docker network connect spring-network mysql
```

#### Start MYSQL docker image

```
docker start mysql
```

#### Start spring boot application using mysql profile

```
docker run -d -p 9090:9090 ^
--network spring-network ^
-e SPRING_PROFILES_ACTIVE=mysql ^
-e "SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/spring-boot-learn?useSSL=false&serverTimezone=UTC" ^
--name spring-boot-learn-mysql ^
spring-boot-learn
```

### Step 7 - test the app

```
GET: http://localhost:9090/health-check
GET: http://localhost:9090/courses
GET: http://localhost:9090/books
```

### Step 8 - Stop the containers (no need to remove the network 'spring-network')

```
nitinb@Nitin:~$ docker ps
CONTAINER ID   IMAGE               COMMAND                  CREATED          STATUS          PORTS                                         NAMES
00f05abeb067   spring-boot-learn   "java -jar /app/app.…"   13 minutes ago   Up 13 minutes   0.0.0.0:9090->9090/tcp, [::]:9090->9090/tcp   spring-boot-learn-mysql
71b0fa82b68c   mysql:5.7           "docker-entrypoint.s…"   7 months ago     Up 15 minutes   0.0.0.0:3306->3306/tcp, [::]:3306->3306/tcp   mysql
nitinb@Nitin:~$ docker container stop spring-boot-learn-mysql
spring-boot-learn-mysql
nitinb@Nitin:~$ docker container stop mysql
mysql

```

### Step 9 - Alternative to Step 6 & 7 where we create a common docker image

We can modify the application.properties and appliation-mysql.properties and create separate images (not recommended but is simple to use)

#### Step 9.a build spring-boot-learn-h2 docker image

- in application.properties set spring.profiles.active=h2
- run below command

```
cd ..\spring-boot-learn
mvn clean package -DskipTests
docker build -t spring-boot-learn-h2 .
```

run the container as

```
docker run -d -p 9090:9090 spring-boot-learn-h2
```

#### Step 9.b build spring-boot-learn-mysql docker image

- in application.properties set spring.profiles.active=mysql
- in application-mysql.properties set spring.datasource.url=jdbc:mysql://mysql:3306/spring-boot-learn?useSSL=false&serverTimezone=UTC in and run below command
- run below command

```
cd ..\spring-boot-learn
mvn clean package -DskipTests
docker build -t spring-boot-learn-mysql .
```

Create network (only once same as mentioned under step 6)

```
docker network create spring-network
docker network connect spring-network mysql
```

Start MYSQL docker image

```
docker start mysql
```

run the container as

```
docker run -d --network spring-network  -p 9090:9090  spring-boot-learn-mysql
```

