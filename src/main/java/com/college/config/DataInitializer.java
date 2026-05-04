package com.college.config;

import com.college.model.*;
import com.college.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // Check if users already exist
        if (userRepository.count() == 0) {
            System.out.println("========================================");
            System.out.println("Initializing Database with Sample Data...");
            System.out.println("========================================");
            
            try {
                // 1. Create ADMIN User
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                admin.setEmail("admin@college.com");
                admin.setFullName("System Administrator");
                admin.setIsActive(true);
                admin.setCreatedAt(LocalDateTime.now());
                User savedAdmin = userRepository.save(admin);
                System.out.println("✓ Admin user created with ID: " + savedAdmin.getId());
                
                // 2. Create FACULTY User
                User facultyUser = new User();
                facultyUser.setUsername("faculty");
                facultyUser.setPassword("faculty123");
                facultyUser.setRole("FACULTY");
                facultyUser.setEmail("faculty@college.com");
                facultyUser.setFullName("Dr. Robert Johnson");
                facultyUser.setIsActive(true);
                facultyUser.setCreatedAt(LocalDateTime.now());
                User savedFacultyUser = userRepository.save(facultyUser);
                System.out.println("✓ Faculty user created with ID: " + savedFacultyUser.getId());
                
                // 3. Create FACULTY Profile
                Faculty faculty = new Faculty();
                faculty.setFacultyId("FAC001");
                faculty.setName("Dr. Robert Johnson");
                faculty.setEmail("faculty@college.com");
                faculty.setPhone("9876543210");
                faculty.setDepartment("Computer Science");
                faculty.setDesignation("Professor");
                faculty.setQualification("Ph.D");
                faculty.setUser(savedFacultyUser);
                Faculty savedFaculty = facultyRepository.save(faculty);
                System.out.println("✓ Faculty profile created with ID: " + savedFaculty.getId());
                
                // 4. Create STUDENT User
                User studentUser = new User();
                studentUser.setUsername("student");
                studentUser.setPassword("student123");
                studentUser.setRole("STUDENT");
                studentUser.setEmail("student@college.com");
                studentUser.setFullName("John Doe");
                studentUser.setIsActive(true);
                studentUser.setCreatedAt(LocalDateTime.now());
                User savedStudentUser = userRepository.save(studentUser);
                System.out.println("✓ Student user created with ID: " + savedStudentUser.getId());
                
                // 5. Create STUDENT Profile
                Student student = new Student();
                student.setStudentId("STU001");
                student.setName("John Doe");
                student.setEmail("student@college.com");
                student.setPhone("1234567890");
                student.setDepartment("Computer Science");
                student.setSemester("5th Semester");
                student.setBatch("2022");
                student.setSection("A");
                student.setUser(savedStudentUser);
                Student savedStudent = studentRepository.save(student);
                System.out.println("✓ Student profile created with ID: " + savedStudent.getId());
                
                // 6. Create Second STUDENT User
                User studentUser2 = new User();
                studentUser2.setUsername("student2");
                studentUser2.setPassword("student123");
                studentUser2.setRole("STUDENT");
                studentUser2.setEmail("jane@college.edu");
                studentUser2.setFullName("Jane Smith");
                studentUser2.setIsActive(true);
                studentUser2.setCreatedAt(LocalDateTime.now());
                User savedStudentUser2 = userRepository.save(studentUser2);
                
                // 7. Create Second STUDENT Profile
                Student student2 = new Student();
                student2.setStudentId("STU002");
                student2.setName("Jane Smith");
                student2.setEmail("jane@college.edu");
                student2.setPhone("0987654321");
                student2.setDepartment("Computer Science");
                student2.setSemester("5th Semester");
                student2.setBatch("2022");
                student2.setSection("A");
                student2.setUser(savedStudentUser2);
                studentRepository.save(student2);
                System.out.println("✓ Second student profile created");
                
                // 8. Create Courses
                Course course1 = new Course();
                course1.setCourseCode("CS301");
                course1.setCourseName("Data Structures");
                course1.setDescription("Advanced data structures and algorithms");
                course1.setCredits(4);
                course1.setDepartment("Computer Science");
                course1.setSemester("5th Semester");
                course1.setCourseType("THEORY");
                course1.setFaculty(savedFaculty);
                courseRepository.save(course1);
                
                Course course2 = new Course();
                course2.setCourseCode("CS302");
                course2.setCourseName("Database Systems");
                course2.setDescription("Database design and SQL");
                course2.setCredits(4);
                course2.setDepartment("Computer Science");
                course2.setSemester("5th Semester");
                course2.setCourseType("THEORY");
                course2.setFaculty(savedFaculty);
                courseRepository.save(course2);
                
                Course course3 = new Course();
                course3.setCourseCode("CS303");
                course3.setCourseName("Web Development");
                course3.setDescription("HTML, CSS, JavaScript, Spring Boot");
                course3.setCredits(3);
                course3.setDepartment("Computer Science");
                course3.setSemester("5th Semester");
                course3.setCourseType("PRACTICAL");
                course3.setFaculty(savedFaculty);
                courseRepository.save(course3);
                
                System.out.println("✓ Courses created");
                System.out.println("========================================");
                System.out.println("DATABASE INITIALIZATION COMPLETE!");
                System.out.println("========================================");
                System.out.println("Login Credentials:");
                System.out.println("Admin   → username: admin   | password: admin123");
                System.out.println("Faculty → username: faculty | password: faculty123");
                System.out.println("Student → username: student | password: student123");
                System.out.println("Student2 → username: student2 | password: student123");
                System.out.println("========================================");
                
            } catch (Exception e) {
                System.err.println("Error initializing data: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Database already contains " + userRepository.count() + " users. Skipping initialization.");
        }
    }
}