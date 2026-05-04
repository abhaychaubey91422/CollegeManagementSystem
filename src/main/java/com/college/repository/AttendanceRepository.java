package com.college.repository;

import com.college.model.Attendance;
import com.college.model.Student;
import com.college.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
    List<Attendance> findByCourse(Course course);
    List<Attendance> findByStudentAndCourse(Student student, Course course);
    boolean existsByStudentAndCourseAndDate(Student student, Course course, LocalDate date);
}