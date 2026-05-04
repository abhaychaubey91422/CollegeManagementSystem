package com.college.service;

import com.college.model.Attendance;
import com.college.model.Student;
import com.college.model.Course;
import com.college.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
    
    public List<Attendance> getAttendanceByStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }
    
    public List<Attendance> getAttendanceByStudentAndCourse(Student student, Course course) {
        return attendanceRepository.findByStudentAndCourse(student, course);
    }
}