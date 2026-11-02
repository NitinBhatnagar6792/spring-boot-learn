package com.nb.spring.boot.learn.startup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nb.spring.boot.learn.dto.CourseResponse;
import com.nb.spring.boot.learn.entities.Course;
import com.nb.spring.boot.learn.repositories.CourseJdbcRepository;
import com.nb.spring.boot.learn.repositories.CourseJpaRepository;
import com.nb.spring.boot.learn.repositories.CourseSpringDataJpaRepository;
import com.nb.spring.boot.learn.repositories.CourseSpringJdbcRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The CommandLineRunner is generally used to execute some initialization logic when the application starts.
 *	Some common use cases:
 *	- Insert initial/default records into the database.
 *	- Load master data.
 *	- Perform database initialization.
 *	- Execute startup validation.
 *	- Run some background initialization logic.
 */
@Component
@Slf4j
public class CourseDatabaseCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private CourseJdbcRepository courseJdbcRepository;

	@Autowired
	private CourseSpringJdbcRepository courseSpringJdbcRepository;
	
	@Autowired
	private CourseJpaRepository courseJpaRepository;
	
	@Autowired
	private CourseSpringDataJpaRepository courseSpringDataJpaRepository;
	
	private final static String AI_COURSE_NAME = "Learn AI";

	/**
	 * In Spring Boot, the run() method of CommandLineRunner is automatically called
	 * once the Spring Boot application has started successfully and the
	 * Spring ApplicationContext has been initialized.
	 */
	public void run(String... args) throws Exception {
		
		dbOperationsUsingJdbc();
		
		dbOperationsUsingSpringJdbc();
		
		List<Course> newCourses = dbOperationsUsingJPA();
		
		dbOperationsUsingSpringDataJPA(newCourses);
	}

	private void dbOperationsUsingJdbc() {
		
		// Sample use of courseJdbcRepository
		courseJdbcRepository.insert(AI_COURSE_NAME, "Adam");
		
		CourseResponse courseResponse = courseJdbcRepository.findFirstByName(AI_COURSE_NAME);
		log.info("courseResponse: {}", courseResponse);
		
	}
	
	private void dbOperationsUsingSpringJdbc() {
		
		// Sample use of courseSpringJdbcRepository
		courseSpringJdbcRepository.insert("Learn Math", "Sampat");
		courseSpringJdbcRepository.insert("Learn Physics", "Hopman");
		
		CourseResponse courseResponse = courseSpringJdbcRepository.findFirstByName("Learn Math");
		log.info("courseResponse: {}", courseResponse);
		
		courseSpringJdbcRepository.deleteById(courseResponse.getId());
	}

	private List<Course> dbOperationsUsingJPA() {

		// Sample use of courseJpaRepository
		Course chemistryCourse = courseJpaRepository.insert(Course.builder().name("Learn Chemistry").author("Gate").build());
		log.info("chemistryCourse: {}", chemistryCourse);
		
		Course courseFound  = courseJpaRepository.findById(chemistryCourse.getId());
		log.info("courseFound: {}", courseFound);

		courseJpaRepository.deleteById(chemistryCourse.getId());
		
		Course englishCourse  = courseJpaRepository.insert(Course.builder().name("Learn English").author("Tom").build());
		Course hindiCourse  = courseJpaRepository.insert(Course.builder().name("Learn Hindi").author("Frank").build());
		
		return List.of(englishCourse, hindiCourse);
	}
	
	private void dbOperationsUsingSpringDataJPA(List<Course> newCourses) {
		
		// Sample use of courseSpringDataJpaRepository
		Course englishCourse = courseSpringDataJpaRepository.findById(newCourses.get(0).getId()).get();
		log.info("englishCourse: {}", englishCourse);
		Course hindiCourse = courseSpringDataJpaRepository.findById(newCourses.get(1).getId()).get();
		
		englishCourse.setName(englishCourse.getName()+"-updated");
		courseSpringDataJpaRepository.save(englishCourse);
		courseSpringDataJpaRepository.delete(hindiCourse);

		Course anotherAiCourse = Course.builder().name(AI_COURSE_NAME).author(englishCourse.getAuthor()).build();
		courseSpringDataJpaRepository.save(anotherAiCourse);
		
		List<Course> aiCourses = courseSpringDataJpaRepository.findByNameOrderByAuthorDesc(AI_COURSE_NAME);
		
		log.info("all AI Courses: {}", aiCourses);
		
	}
}
