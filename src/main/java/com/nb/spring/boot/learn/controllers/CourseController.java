package com.nb.spring.boot.learn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nb.spring.boot.learn.dto.CourseResponse;
import com.nb.spring.boot.learn.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public List<CourseResponse> getAllCourses() {
		return courseService.getAllCourses();
	}
}
