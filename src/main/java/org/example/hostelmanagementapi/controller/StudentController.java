package org.example.hostelmanagementapi.controller;

import org.example.hostelmanagementapi.Student;
import org.example.hostelmanagementapi.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    // POST: Insert Student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    // GET: Retrieve All Students
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // GET: Retrieve Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        return repository.findById(id).map(student -> {

            student.setHostelName(updatedStudent.getHostelName());
            student.setRoomNumber(updatedStudent.getRoomNumber());
            student.setFee(updatedStudent.getFee());

            Student savedStudent = repository.save(student);

            return ResponseEntity.ok(savedStudent);

        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully!");
        }

        return ResponseEntity.status(404)
                .body("Student not found!");
    }
}