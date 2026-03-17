package com.nb.spring.boot.learn.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nb.spring.boot.learn.repositories.CourseJdbcRepository;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJdbcRepository courseJdbcRepository;
	@Override
	public void run(String... args) throws Exception {
		
		courseJdbcRepository.insert("Learn Math", "Sampat");
		
	}

}
