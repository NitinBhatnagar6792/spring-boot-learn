package com.nb.spring.boot.learn.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nb.spring.boot.learn.dto.CourseResponse;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@GetMapping
	public List<CourseResponse> getAllCourses() {
		
		return List.of(
				CourseResponse.builder().id(1).name("Learn AWS").author("Jack").build(),
				CourseResponse.builder().id(2).name("Learn Java").author("Bruce").build()
				);
	}
}
