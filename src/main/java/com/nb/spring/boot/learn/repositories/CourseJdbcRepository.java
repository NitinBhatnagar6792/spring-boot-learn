package com.nb.spring.boot.learn.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nb.spring.boot.learn.dto.CourseResponse;

@Repository
public class CourseJdbcRepository {

	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private static String INSERT_QUERY = """
			INSERT INTO courses (name, author)
			SELECT ?, ?
			WHERE NOT EXISTS (
			    SELECT 1 FROM courses WHERE name = ?
			);
			""";

	private static String DELETE_QUERY = """
			DELETE FROM courses
			    WHERE id = ?
			""";

	private static String FIND_FIRST_BY_NAME = """
			SELECT * FROM courses
			    WHERE name = ?
			    LIMIT 1
			""";
	
	
//	private static String createInsertQuery(String name, String author) {
//		return INSERT_QUERY.formatted(name, author, name);
//	}

	public void insert(String name, String author) {
		//String inserStmt = createInsertQuery(name,author);
		springJdbcTemplate.update(INSERT_QUERY, name, author, name);
	}

	public void deleteById(long id) {
		springJdbcTemplate.update(DELETE_QUERY, id);
	}

	public CourseResponse findFirstByName(String name) {
		return springJdbcTemplate.queryForObject(FIND_FIRST_BY_NAME,
				new BeanPropertyRowMapper<>(CourseResponse.class),
				name);
	}
	
}
