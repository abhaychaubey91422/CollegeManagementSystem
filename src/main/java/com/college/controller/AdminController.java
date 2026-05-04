package com.college.controller;

import com.college.model.*;
import com.college.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("totalFaculty", facultyService.getAllFaculty().size());
        model.addAttribute("totalCourses", courseService.getAllCourses().size());
        return "admin/dashboard";
    }
    
    // Student Management
    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        return "admin/students";
    }
    
    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String email) {
        User user = new User(username, password, "STUDENT");
        user.setEmail(email);
        user.setFullName(student.getName());
        userService.saveUser(user);
        
        student.setUser(user);
        student.setEmail(email);
        studentService.saveStudent(student);
        
        return "redirect:/admin/students";
    }
    
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }
    
    // Faculty Management
    @GetMapping("/faculty")
    public String faculty(Model model) {
        model.addAttribute("facultyList", facultyService.getAllFaculty());
        model.addAttribute("faculty", new Faculty());
        return "admin/faculty";
    }
    
    @PostMapping("/faculty/add")
    public String addFaculty(@ModelAttribute Faculty faculty,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String email) {
        User user = new User(username, password, "FACULTY");
        user.setEmail(email);
        user.setFullName(faculty.getName());
        userService.saveUser(user);
        
        faculty.setUser(user);
        faculty.setEmail(email);
        facultyService.saveFaculty(faculty);
        
        return "redirect:/admin/faculty";
    }
    
    @GetMapping("/faculty/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/admin/faculty";
    }
    
    // Course Management
    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("facultyList", facultyService.getAllFaculty());
        model.addAttribute("course", new Course());
        return "admin/courses";
    }
    
    @PostMapping("/course/add")
    public String addCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }
    
    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses";
    }
}