package com.college.controller;

import com.college.model.User;
import com.college.model.Student;
import com.college.model.Faculty;
import com.college.service.UserService;
import com.college.service.StudentService;
import com.college.service.FacultyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private FacultyService facultyService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        var userOpt = userService.authenticate(username, password);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            session.setAttribute("user", user);
            
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else if ("STUDENT".equals(user.getRole())) {
                var studentOpt = studentService.getStudentByUser(user);
                if (studentOpt.isPresent()) {
                    session.setAttribute("student", studentOpt.get());
                    return "redirect:/student/dashboard";
                }
            } else if ("FACULTY".equals(user.getRole())) {
                var facultyOpt = facultyService.getFacultyByUser(user);
                if (facultyOpt.isPresent()) {
                    session.setAttribute("faculty", facultyOpt.get());
                    return "redirect:/faculty/dashboard";
                }
            }
        }
        
        model.addAttribute("error", "Invalid username or password!");
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}