# health-check API
- curl --location 'http://localhost:9090/health-check'

# Get All Courses
- curl --location 'http://localhost:9090/courses'

# actuator
- curl --location 'http://localhost:9090/actuator'
- curl --location 'http://localhost:9090/actuator/health'
- curl --location 'http://localhost:9090/actuator/metrics'
- curl --location 'http://localhost:9090/actuator/metrics/http.server.requests'


# Get All Books

```
curl --location 'http://localhost:9090/books'
```

# Get Book by id	

```
curl --location 'http://localhost:9090/books/1'
```

# Create new Book

```
curl --location 'http://localhost:9090/books' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "name": "Learn Maths",
    "author": "Jack Man"
}'
```

# Update Book by book name

```
curl --location --request PUT 'http://localhost:9090/books' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "name": "Learn Maths",
    "author": "Jack Moe"
}'
```

# Delete Book by id

```
curl --location --request DELETE 'http://localhost:9090/books/2'
```

