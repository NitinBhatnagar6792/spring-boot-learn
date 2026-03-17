package com.nb.spring.boot.learn.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private static String INSERT_QUERY = """
			INSERT INTO courses (name, author)
			SELECT '%s', '%s'
			WHERE NOT EXISTS (
			    SELECT 1 FROM courses WHERE name = '%s'
			);
			""";
	
	private static String createInsertQuery(String name, String author) {
		return INSERT_QUERY.formatted(name, author, name);
	}

	public void insert(String name, String author) {
		String inserStmt = createInsertQuery(name,author);
		springJdbcTemplate.update(inserStmt);
	}

}
