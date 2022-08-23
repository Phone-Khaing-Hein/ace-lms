package com.ai.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.lms.entity.Course;
import com.ai.lms.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepo;

	public void save(Course course) {
        courseRepo.save(course);
	}
	
	public List<Course> findAll(){
		return courseRepo.findAll();
	}

	public Course findById(int courseId) {
		return courseRepo.findById(courseId).get();
	}

	public Course findByName(String name) {
		return courseRepo.findByName(name);
	}
    
}
