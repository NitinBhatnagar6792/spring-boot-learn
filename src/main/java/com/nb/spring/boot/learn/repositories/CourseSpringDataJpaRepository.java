package com.nb.spring.boot.learn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nb.spring.boot.learn.entities.Course;

@Repository
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{

}
