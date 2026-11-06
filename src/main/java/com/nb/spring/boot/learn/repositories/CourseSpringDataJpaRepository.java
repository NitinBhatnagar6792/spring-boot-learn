package com.nb.spring.boot.learn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nb.spring.boot.learn.entities.Course;

@Repository
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{

	List<Course> findByNameOrderByAuthorDesc(String name);
}
