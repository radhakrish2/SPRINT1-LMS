package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.EnrollmentDto;
import com.learnhub.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Student Enroll Course
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<EnrollmentDto>> enrollCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        EnrollmentDto enrollment = enrollmentService.enrollCourse(studentId, courseId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Course enrolled successfully.",
                        enrollment));
    }

    // Get Student Enrollments
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getEnrollmentsByStudent(
            @PathVariable Long studentId) {

        List<EnrollmentDto> enrollments =
                enrollmentService.getEnrollmentsByStudent(studentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student enrollments retrieved successfully.",
                        enrollments));
    }

    // Get Course Enrollments
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getEnrollmentsByCourse(
            @PathVariable Long courseId) {

        List<EnrollmentDto> enrollments =
                enrollmentService.getEnrollmentsByCourse(courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course enrollments retrieved successfully.",
                        enrollments));
    }

    // Get Specific Enrollment
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<EnrollmentDto>> getEnrollment(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        EnrollmentDto enrollment =
                enrollmentService.getEnrollment(studentId, courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Enrollment retrieved successfully.",
                        enrollment));
    }

    // Cancel Enrollment
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<Void>> cancelEnrollment(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        enrollmentService.cancelEnrollment(studentId, courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Enrollment cancelled successfully.",
                        null));
    }

}