package com.nb.spring.boot.learn.dto;

import com.nb.spring.boot.learn.enums.CourseLanguage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	private long id;
	private String name;
	private String author;
	private CourseLanguage courseLanguage; 
}
