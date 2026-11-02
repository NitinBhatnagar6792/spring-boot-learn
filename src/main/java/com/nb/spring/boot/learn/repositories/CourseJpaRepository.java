package com.nb.spring.boot.learn.repositories;

import org.springframework.stereotype.Repository;

import com.nb.spring.boot.learn.entities.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJpaRepository {
	
	//@Autowired can also work however better to use @PersistenceContext
	@PersistenceContext
	private EntityManager entityManager;

	public Course insert(Course course) {
		return entityManager.merge(course);
	}

	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}

	public Course deleteById(long id) {
		Course course = findById(id);
		if (course != null) {
			entityManager.remove(course);
		}
		return course;
	}
	
}
