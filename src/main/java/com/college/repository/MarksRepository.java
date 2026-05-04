package com.college.repository;

import com.college.model.Marks;
import com.college.model.Student;
import com.college.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {
    List<Marks> findByStudent(Student student);
    List<Marks> findByCourse(Course course);
    List<Marks> findByStudentAndCourse(Student student, Course course);
}