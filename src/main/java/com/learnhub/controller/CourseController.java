package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.CourseDto;
import com.learnhub.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

    private final CourseService courseService;

    // Create Course
    @PostMapping
    public ResponseEntity<ApiResponse<CourseDto>> createCourse(
            @Valid @RequestBody CourseDto courseDto) {

        CourseDto course = courseService.createCourse(courseDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Course created successfully.",
                        course));
    }

    // Update Course
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDto courseDto) {

        CourseDto course = courseService.updateCourse(id, courseDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course updated successfully.",
                        course));
    }

    // Get Course By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourseById(
            @PathVariable Long id) {

        CourseDto course = courseService.getCourseById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course retrieved successfully.",
                        course));
    }

    // Get All Courses
    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDto>>> getAllCourses() {

        List<CourseDto> courses = courseService.getAllCourses();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Courses retrieved successfully.",
                        courses));
    }

    // Get Courses By Instructor
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCoursesByInstructor(
            @PathVariable Long instructorId) {

        List<CourseDto> courses =
                courseService.getCoursesByInstructor(instructorId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor courses retrieved successfully.",
                        courses));
    }

    // Get Courses By Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCoursesByCategory(
            @PathVariable Long categoryId) {

        List<CourseDto> courses =
                courseService.getCoursesByCategory(categoryId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Category courses retrieved successfully.",
                        courses));
    }

    // Delete Course
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course deleted successfully.",
                        null));
    }

}