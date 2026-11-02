# AGENTS.md

## Project Overview

This is a Java backend learning project focused on Java,
Spring Boot, REST APIs, Spring Data JPA, database integration,
and related backend technologies.

Follow established Java and Spring Boot best practices.
Prioritize clean code, readability, maintainability,
and production-quality implementation.

## General Coding Guidelines

- Follow standard Java naming conventions.
- Prefer meaningful class, method, and variable names.
- Follow SOLID principles where appropriate.
- Avoid unnecessary code duplication.
- Prefer constructor injection for Spring dependencies.
- Use Lombok only where it improves readability.
- Avoid unnecessary abstractions and over engineering.
- Do not introduce dependencies without a valid reason.

## Java Guidelines

- Use appropriate Java language features.
- Handle null values and optional data carefully.
- Avoid unnecessary object creation.
- Use try-with-resources for resource management.
- Avoid catching generic exceptions unless justified.
- Avoid System.out.println() for application logging.
- Use appropriate logging levels.

## Spring Boot Guidelines

- Follow Spring Boot conventions and best practices.
- Use appropriate stereotypes such as @Service,
  @Repository, and @RestController.
- Prefer constructor-based dependency injection.
- Keep controllers thin and business logic in service classes.
- Validate incoming REST API requests.
- Use appropriate HTTP status codes and response structures.
- Handle exceptions consistently.
- Keep configuration externalized using application properties
  or YAML files.
- Avoid hardcoding credentials, URLs, and environment-specific values.

## JPA and Database Guidelines

- Review entity mappings and relationship definitions.
- Check appropriate use of @Id and @GeneratedValue.
- Identify potential N+1 query problems.
- Review transaction boundaries.
- Check database queries for correctness and efficiency.
- Avoid unnecessary database calls.
- Verify appropriate nullability and column constraints.
- Consider lazy loading and cascading behavior carefully.

## Exception Handling and Logging

- Ensure exceptions are handled appropriately.
- Avoid silently swallowing exceptions.
- Avoid exposing internal implementation details in API responses.
- Use meaningful error messages.
- Avoid logging passwords, tokens, credentials, or sensitive data.
- Use SLF4J logging instead of System.out.println().

## Testing Guidelines

- Identify missing unit and integration tests.
- Check positive and negative test scenarios.
- Verify edge cases and exception scenarios.
- Prefer meaningful assertions.
- Check whether existing tests adequately cover changed behavior.

## Code Review Rules

### Review Scope

- Review the complete pull request diff between the
  source branch and the target branch.
- Consider all added, modified, and deleted files.
- Do not restrict the review to the latest commit.
- Review the cumulative changes introduced by the PR.
- Consider relevant existing code to understand the impact
  of the changes.
- Do not assume that unchanged code needs reviewing unless
  it is relevant to a changed implementation.

### Review Criteria

- Identify functional bugs and logical errors.
- Identify incorrect or missing exception handling.
- Identify potential null pointer exceptions.
- Check Java and Spring Boot best practices.
- Review REST API implementation and response handling.
- Check JPA mappings and database interactions.
- Identify potential security vulnerabilities.
- Identify performance and resource management issues.
- Identify missing or inadequate test coverage.
- Identify backward compatibility issues where applicable.

### Review Output

- Report actionable findings with clear explanations.
- Include severity: Critical, High, Medium, or Low.
- Provide file names and relevant line references.
- Explain the potential impact of each finding.
- Suggest a practical improvement or fix.
- Avoid reporting purely stylistic preferences.
- Do not report speculative issues without sufficient evidence.
- Do not modify source code or create commits during reviews.

### Review Depth

- Perform a basic, practical code review by default.
- Focus on correctness, maintainability, and common
  production issues.
- Avoid exhaustive architectural or security audits unless
  explicitly requested.
- Do not generate findings merely to increase the number
  of review comments.
