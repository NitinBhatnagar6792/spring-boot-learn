package com.nb.spring.boot.learn.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "course-service")
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
