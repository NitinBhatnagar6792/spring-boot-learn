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

# Steps for configuring code review using [ChatGPT](https://chatgpt.com/settings/code-review)

- In ChatGPT go to [plugins](https://chatgpt.com/plugins?search=github) and search for GitHub
- Install the GitHub plugin while doing so it will ask for github credentials and repos for which you want to give access to chatgpt
- Now create a PR and add a comment starting with '@codex review' to trigger the code review

```
@codex review: Do a very basic code review
@codex review: Please review the code written in file src/main/java/com/nb/spring/boot/learn/services/CourseService.java as part of this PR, suggest some improvement however do not make any change to the code
```

