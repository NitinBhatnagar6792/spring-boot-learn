package com.nb.spring.boot.learn.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nb.spring.boot.learn.dto.CourseResponse;

@Repository
public class CourseSpringJdbcRepository {

	@Autowired
	private JdbcTemplate springJdbcTemplate;

	public void insert(String name, String author) {
		springJdbcTemplate.update(SQLConstants.CourseQueries.INSERT_QUERY, name, author, name);
	}

	public void deleteById(long id) {
		springJdbcTemplate.update(SQLConstants.CourseQueries.DELETE_QUERY, id);
	}

	public CourseResponse findFirstByName(String name) {
		return springJdbcTemplate.queryForObject(SQLConstants.CourseQueries.FIND_FIRST_BY_NAME,
				new BeanPropertyRowMapper<>(CourseResponse.class),
				name);
	}
	
}
