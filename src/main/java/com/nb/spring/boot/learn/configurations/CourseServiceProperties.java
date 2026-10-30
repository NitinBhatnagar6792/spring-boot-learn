package com.nb.spring.boot.learn.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "course-service")
@Component
public class CourseServiceProperties {

	private Integer maxCourses;
	private String defaultLanguage;

	public Integer getMaxCourses() {
		return maxCourses;
	}

	public void setMaxCourses(Integer maxCourses) {
		this.maxCourses = maxCourses;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
}
