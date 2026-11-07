package org.example.hostelmanagementapi.repository;

import org.example.hostelmanagementapi.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}