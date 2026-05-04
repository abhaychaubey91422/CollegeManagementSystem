package com.college.repository;

import com.college.model.Faculty;
import com.college.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findByFacultyId(String facultyId);
    Optional<Faculty> findByUser(User user);
    List<Faculty> findByDepartment(String department);
}