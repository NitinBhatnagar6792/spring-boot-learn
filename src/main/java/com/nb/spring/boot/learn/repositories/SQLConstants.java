package com.nb.spring.boot.learn.repositories;

public class SQLConstants {

	static class CourseQueries {
		public static final String INSERT_QUERY = """
				INSERT INTO courses (name, author)
				SELECT ?, ?
				WHERE NOT EXISTS (
				    SELECT 1 FROM courses WHERE name = ?
				);
				""";

		public static final  String DELETE_QUERY = """
				DELETE FROM courses
				    WHERE id = ?
				""";

		public static final String FIND_FIRST_BY_NAME = """
				SELECT * FROM courses
				    WHERE name = ?
				    LIMIT 1
				""";
	}
}
