package com.college.controller;

import com.college.model.*;
import com.college.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private MarksService marksService;
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        if (faculty == null) return "redirect:/login";
        
        var courses = courseService.getCoursesByFaculty(faculty);
        model.addAttribute("faculty", faculty);
        model.addAttribute("courses", courses);
        return "faculty/dashboard";
    }
    
    @GetMapping("/attendance")
    public String attendancePage(HttpSession session, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        if (faculty == null) return "redirect:/login";
        
        model.addAttribute("courses", courseService.getCoursesByFaculty(faculty));
        model.addAttribute("students", studentService.getAllStudents());
        return "faculty/attendance";
    }
    
    @PostMapping("/attendance/mark")
    public String markAttendance(@RequestParam Long studentId,
                                @RequestParam Long courseId,
                                @RequestParam String status,
                                @RequestParam(required = false) String remarks) {
        var studentOpt = studentService.getStudentById(studentId);
        var courseOpt = courseService.getCourseById(courseId);
        
        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Attendance attendance = new Attendance();
            attendance.setStudent(studentOpt.get());
            attendance.setCourse(courseOpt.get());
            attendance.setStatus(status);
            attendance.setRemarks(remarks);
            attendanceService.markAttendance(attendance);
        }
        return "redirect:/faculty/attendance";
    }
    
    @GetMapping("/marks")
    public String marksPage(HttpSession session, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        if (faculty == null) return "redirect:/login";
        
        model.addAttribute("courses", courseService.getCoursesByFaculty(faculty));
        model.addAttribute("students", studentService.getAllStudents());
        return "faculty/marks";
    }
    
    @PostMapping("/marks/add")
    public String addMarks(@RequestParam Long studentId,
                          @RequestParam Long courseId,
                          @RequestParam String examType,
                          @RequestParam Double marksObtained,
                          @RequestParam Double totalMarks) {
        var studentOpt = studentService.getStudentById(studentId);
        var courseOpt = courseService.getCourseById(courseId);
        
        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Marks marks = new Marks();
            marks.setStudent(studentOpt.get());
            marks.setCourse(courseOpt.get());
            marks.setExamType(examType);
            marks.setMarksObtained(marksObtained);
            marks.setTotalMarks(totalMarks);
            marksService.saveMarks(marks);
        }
        return "redirect:/faculty/marks";
    }
    
    @GetMapping("/courses")
    public String courses(HttpSession session, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        if (faculty == null) return "redirect:/login";
        
        model.addAttribute("courses", courseService.getCoursesByFaculty(faculty));
        return "faculty/courses";
    }
}