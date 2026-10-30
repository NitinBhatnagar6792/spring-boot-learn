package com.nb.spring.boot.learn.jdbc;

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
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

	
	@Autowired
	private CourseJdbcRepository courseJdbcRepository;

	@Autowired
	private CourseSpringJdbcRepository courseSpringJdbcRepository;
	
	@Autowired
	private CourseJpaRepository courseJpaRepository;
	
	@Autowired
	private CourseSpringDataJpaRepository courseSpringDataJpaRepository;
	
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
		courseJdbcRepository.insert("Learn AI", "Adam");
		
		CourseResponse courseResponse = courseJdbcRepository.findFirstByName("Learn AI");
		System.out.println("courseResponse:"+courseResponse);
		
	}
	
	private void dbOperationsUsingSpringJdbc() {
		
		// Sample use of courseSpringJdbcRepository
		courseSpringJdbcRepository.insert("Learn Math", "Sampat");
		courseSpringJdbcRepository.insert("Learn Physics", "Hopman");
		
		CourseResponse courseResponse = courseSpringJdbcRepository.findFirstByName("Learn Math");
		System.out.println("courseResponse:"+courseResponse);
		
		courseSpringJdbcRepository.deleteById(courseResponse.getId());
	}

	private List<Course> dbOperationsUsingJPA() {

		// Sample use of courseJpaRepository
		Course chemistryCourse = courseJpaRepository.insert(Course.builder().name("Learn Chemistry").author("Gate").build());
		System.out.println("chemistryCourse="+chemistryCourse);
		
		Course courseFound  = courseJpaRepository.findById(chemistryCourse.getId());
		System.out.println("courseFound="+courseFound);

		courseJpaRepository.deleteById(chemistryCourse.getId());
		
		Course englishCourse  = courseJpaRepository.insert(Course.builder().name("Learn English").author("Tom").build());
		Course hindiCourse  = courseJpaRepository.insert(Course.builder().name("Learn Hindi").author("Frank").build());
		
		return List.of(englishCourse, hindiCourse);
	}
	
	private void dbOperationsUsingSpringDataJPA(List<Course> newCourses) {
		
		// Sample use of courseSpringDataJpaRepository
		Course englishCourse = courseSpringDataJpaRepository.findById(newCourses.get(0).getId()).get();
		System.out.println("englishCourse="+englishCourse);
		Course hindiCourse = courseSpringDataJpaRepository.findById(newCourses.get(1).getId()).get();
		
		englishCourse.setName(englishCourse.getName()+"-updated");
		courseSpringDataJpaRepository.save(englishCourse);
		courseSpringDataJpaRepository.delete(hindiCourse);

	}
}
