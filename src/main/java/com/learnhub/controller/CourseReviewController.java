package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.CourseReviewDto;
import com.learnhub.service.CourseReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseReviewController {

    private final CourseReviewService courseReviewService;

    // Add Review
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<CourseReviewDto>> addReview(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestBody CourseReviewDto dto) {

        CourseReviewDto review =
                courseReviewService.addReview(studentId, courseId, dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Review added successfully.",
                        review));
    }

    // Update Review
    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<CourseReviewDto>> updateReview(
            @PathVariable Long reviewId,
            @RequestBody CourseReviewDto dto) {

        CourseReviewDto review =
                courseReviewService.updateReview(reviewId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Review updated successfully.",
                        review));
    }

    // Get Review By Id
    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<CourseReviewDto>> getReviewById(
            @PathVariable Long reviewId) {

        CourseReviewDto review =
                courseReviewService.getReviewById(reviewId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Review retrieved successfully.",
                        review));
    }

    // Get Reviews By Course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<CourseReviewDto>>> getReviewsByCourse(
            @PathVariable Long courseId) {

        List<CourseReviewDto> reviews =
                courseReviewService.getReviewsByCourse(courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course reviews retrieved successfully.",
                        reviews));
    }

    // Get Reviews By Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<CourseReviewDto>>> getReviewsByStudent(
            @PathVariable Long studentId) {

        List<CourseReviewDto> reviews =
                courseReviewService.getReviewsByStudent(studentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student reviews retrieved successfully.",
                        reviews));
    }

    // Delete Review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(
            @PathVariable Long reviewId) {

        courseReviewService.deleteReview(reviewId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Review deleted successfully.",
                        null));
    }

}