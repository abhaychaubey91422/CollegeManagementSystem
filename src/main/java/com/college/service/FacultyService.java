package com.college.service;

import com.college.model.Faculty;
import com.college.model.User;
import com.college.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    
    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }
    
    public Optional<Faculty> getFacultyByUser(User user) {
        return facultyRepository.findByUser(user);
    }
    
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
    
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}