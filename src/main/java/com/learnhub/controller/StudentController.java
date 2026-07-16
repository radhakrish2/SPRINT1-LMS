package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.StudentDto;
import com.learnhub.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    // Create Student Profile
    @PostMapping
    public ResponseEntity<ApiResponse<StudentDto>> createStudent(
            @Valid @RequestBody StudentDto studentDto) {

        StudentDto student = studentService.createStudent(studentDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Student created successfully.",
                        student));
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {

        StudentDto student = studentService.updateStudent(id, studentDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student updated successfully.",
                        student));
    }

    // Get Student By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentById(
            @PathVariable Long id) {

        StudentDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student retrieved successfully.",
                        student));
    }

    // Get Student By Email
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentByEmail(
            @PathVariable String email) {

        StudentDto student = studentService.getStudentByEmail(email);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student retrieved successfully.",
                        student));
    }

    // Get Student By User Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentByUserId(
            @PathVariable Long userId) {

        StudentDto student = studentService.getStudentByUserId(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student retrieved successfully.",
                        student));
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDto>>> getAllStudents() {

        List<StudentDto> students = studentService.getAllStudents();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Students retrieved successfully.",
                        students));
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student deleted successfully.",
                        null));
    }

}