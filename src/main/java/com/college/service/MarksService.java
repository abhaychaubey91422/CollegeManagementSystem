package com.college.service;

import com.college.model.Marks;
import com.college.model.Student;
import com.college.model.Course;
import com.college.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarksService {
    
    @Autowired
    private MarksRepository marksRepository;
    
    public Marks saveMarks(Marks marks) {
        if (marks.getMarksObtained() != null && marks.getTotalMarks() != null) {
            double percentage = (marks.getMarksObtained() / marks.getTotalMarks()) * 100;
            marks.setPercentage(percentage);
            
            if (percentage >= 90) marks.setGrade("A+");
            else if (percentage >= 80) marks.setGrade("A");
            else if (percentage >= 70) marks.setGrade("B+");
            else if (percentage >= 60) marks.setGrade("B");
            else if (percentage >= 50) marks.setGrade("C");
            else marks.setGrade("F");
        }
        return marksRepository.save(marks);
    }
    
    public List<Marks> getMarksByStudent(Student student) {
        return marksRepository.findByStudent(student);
    }
    
    public List<Marks> getMarksByStudentAndCourse(Student student, Course course) {
        return marksRepository.findByStudentAndCourse(student, course);
    }
}