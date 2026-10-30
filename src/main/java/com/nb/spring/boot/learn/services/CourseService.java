package com.nb.spring.boot.learn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.nb.spring.boot.learn.configurations.CourseServiceProperties;
import com.nb.spring.boot.learn.dto.CourseDTO;
import com.nb.spring.boot.learn.dto.CourseResponse;
import com.nb.spring.boot.learn.enums.CourseLanguage;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties(prefix = "course-service")
@Slf4j
public class CourseService {

	@Autowired
	private CourseServiceProperties properties;
	
	private List<CourseDTO> allCourses;

	@PostConstruct
	public void init() {
		allCourses = List.of(new CourseDTO(1, "Learn AWS", "Jack", CourseLanguage.English),
				new CourseDTO(2, "Learn AWS in Japanese", "Jack", CourseLanguage.Japanese),
				new CourseDTO(3, "Learn AWS in French", "Jack", CourseLanguage.French),
				new CourseDTO(4, "Learn Java", "Bruce", CourseLanguage.English),
				new CourseDTO(5, "Learn Java in japanese", "Bruce", CourseLanguage.Japanese),
				new CourseDTO(6, "Learn Oracle", "Caterine", CourseLanguage.English),
				new CourseDTO(7, "Learn Oracle in french", "Caterine", CourseLanguage.French));
	}

	public List<CourseResponse> getAllCourses() {

        Integer maxCourses = properties.getMaxCourses();
        String defaultLanguage = properties.getDefaultLanguage();

        log.info("maxCourses=" + maxCourses);
        log.info("defaultLanguage=" + defaultLanguage);
		
		final CourseLanguage filterOnLanguage = getCourseLanguage();
		return allCourses.stream()
			.filter(c -> filterOnLanguage == c.getCourseLanguage())
			.map(c -> new CourseResponse(c.getId(), c.getName(), c.getAuthor()))
			.limit(maxCourses)
			.toList();
	}

	private CourseLanguage getCourseLanguage() {
		
		try {
			return CourseLanguage.valueOf(properties.getDefaultLanguage());
		} catch (Exception e) {
			System.out.println("Error while converting value to enum"+properties.getDefaultLanguage());
		}
		return CourseLanguage.English;
	}
	
}
