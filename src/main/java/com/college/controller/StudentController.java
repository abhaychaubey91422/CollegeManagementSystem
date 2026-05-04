package com.college.controller;

import com.college.model.Student;
import com.college.service.AttendanceService;
import com.college.service.MarksService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private MarksService marksService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";
        
        model.addAttribute("student", student);
        return "student/dashboard";
    }
    
    @GetMapping("/attendance")
    public String attendance(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";
        
        var attendanceList = attendanceService.getAttendanceByStudent(student);
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("student", student);
        return "student/attendance";
    }
    
    @GetMapping("/marks")
    public String marks(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";
        
        var marksList = marksService.getMarksByStudent(student);
        model.addAttribute("marksList", marksList);
        model.addAttribute("student", student);
        return "student/marks";
    }
    
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";
        
        model.addAttribute("student", student);
        return "student/profile";
    }
}