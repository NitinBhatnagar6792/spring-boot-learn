package com.nb.spring.boot.learn.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nb.spring.boot.learn.dto.CourseResponse;
import com.nb.spring.boot.learn.entities.Course;
import com.nb.spring.boot.learn.repositories.CourseJdbcRepository;
import com.nb.spring.boot.learn.repositories.CourseJpaRepository;
import com.nb.spring.boot.learn.repositories.CourseSpringDataJpaRepository;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJdbcRepository courseJdbcRepository;
	
	@Autowired
	private CourseJpaRepository courseJpaRepository;
	
	@Autowired
	private CourseSpringDataJpaRepository courseSpringDataJpaRepository;
	
	public void run(String... args) throws Exception {
		
		courseJdbcRepository.insert("Learn Math", "Sampat");
		courseJdbcRepository.insert("Learn Physics", "Hopman");
		
		CourseResponse courseResponse = courseJdbcRepository.findFirstByName("Learn Math");
		System.out.println("courseResponse:"+courseResponse);
		
		courseJdbcRepository.deleteById(courseResponse.getId());
		
		
		Course chemistryCourse = courseJpaRepository.insert(Course.builder().name("Learn Chemistry").author("Gate").build());
		System.out.println("chemistryCourse="+chemistryCourse);
		
		Course courseFound  = courseJpaRepository.findById(chemistryCourse.getId());
		System.out.println("courseFound="+courseFound);

		courseJpaRepository.deleteById(chemistryCourse.getId());
		
		
		Course englishCourse = courseJpaRepository.insert(Course.builder().name("Learn English").author("Tom").build());
		Course hindiCourse = courseJpaRepository.insert(Course.builder().name("Learn Hindi").author("Frank").build());
		englishCourse = courseSpringDataJpaRepository.save(englishCourse);
		System.out.println("englishCourse="+englishCourse);
		hindiCourse = courseSpringDataJpaRepository.save(hindiCourse);
		
		englishCourse.setName(englishCourse.getName()+"-updated");
		courseSpringDataJpaRepository.save(englishCourse);
		courseSpringDataJpaRepository.delete(hindiCourse);
		
	}

}
