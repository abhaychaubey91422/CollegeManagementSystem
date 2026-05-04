package com.college.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String studentId;
    
    @Column(nullable = false)
    private String name;
    
    private String email;
    private String phone;
    private String department;
    private String semester;
    private String batch;
    private String section;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    private String address;
    private String parentName;
    private String parentPhone;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;
    
    @OneToMany(mappedBy = "student")
    private List<Marks> marks;
    
    public Student() {}
    
    public Student(String studentId, String name, String email, String department, String semester) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.semester = semester;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
    
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
    
    public String getParentPhone() { return parentPhone; }
    public void setParentPhone(String parentPhone) { this.parentPhone = parentPhone; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public List<Attendance> getAttendances() { return attendances; }
    public void setAttendances(List<Attendance> attendances) { this.attendances = attendances; }
    
    public List<Marks> getMarks() { return marks; }
    public void setMarks(List<Marks> marks) { this.marks = marks; }
}