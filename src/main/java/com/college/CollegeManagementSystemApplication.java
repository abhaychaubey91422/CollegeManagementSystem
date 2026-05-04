package com.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CollegeManagementSystemApplication extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        SpringApplication.run(CollegeManagementSystemApplication.class, args);
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     College Management System Started Successfully!      ║");
        System.out.println("║     Access at: http://localhost:8080                     ║");
        System.out.println("║                                                          ║");
        System.out.println("║     Default Credentials:                                 ║");
        System.out.println("║     Admin: admin / admin123                              ║");
        System.out.println("║     Faculty: faculty / faculty123                        ║");
        System.out.println("║     Student: student / student123                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}