package com.college.model;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    private String examType;
    private Double marksObtained;
    private Double totalMarks;
    private Double percentage;
    private String grade;
    private String remarks;
    private String addedBy;
    
    public Marks() {}
    
    public void calculatePercentageAndGrade() {
        if (marksObtained != null && totalMarks != null && totalMarks > 0) {
            this.percentage = (marksObtained / totalMarks) * 100;
            
            if (percentage >= 90) this.grade = "A+";
            else if (percentage >= 80) this.grade = "A";
            else if (percentage >= 75) this.grade = "B+";
            else if (percentage >= 70) this.grade = "B";
            else if (percentage >= 60) this.grade = "C+";
            else if (percentage >= 50) this.grade = "C";
            else if (percentage >= 45) this.grade = "D";
            else this.grade = "F";
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    
    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }
    
    public Double getMarksObtained() { return marksObtained; }
    public void setMarksObtained(Double marksObtained) { 
        this.marksObtained = marksObtained;
        calculatePercentageAndGrade();
    }
    
    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { 
        this.totalMarks = totalMarks;
        calculatePercentageAndGrade();
    }
    
    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getAddedBy() { return addedBy; }
    public void setAddedBy(String addedBy) { this.addedBy = addedBy; }
}