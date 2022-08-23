package com.ai.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.lms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	Course findByName(String name);
    
}
