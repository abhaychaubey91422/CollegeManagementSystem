package com.college.service;

import com.college.model.Course;
import com.college.model.Faculty;
import com.college.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public List<Course> getCoursesByFaculty(Faculty faculty) {
        return courseRepository.findByFaculty(faculty);
    }
    
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}