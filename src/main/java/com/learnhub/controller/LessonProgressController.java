package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.LessonProgressDto;
import com.learnhub.service.LessonProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lesson-progress")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LessonProgressController {

    private final LessonProgressService lessonProgressService;

    // Create Lesson Progress
    @PostMapping("/student/{studentId}/lesson/{lessonId}")
    public ResponseEntity<ApiResponse<LessonProgressDto>> createLessonProgress(
            @PathVariable Long studentId,
            @PathVariable Long lessonId) {

        LessonProgressDto progress =
                lessonProgressService.createLessonProgress(studentId, lessonId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Lesson progress created successfully.",
                        progress));
    }

    // Mark Video Completed
    @PutMapping("/{progressId}/video")
    public ResponseEntity<ApiResponse<LessonProgressDto>> markVideoCompleted(
            @PathVariable Long progressId) {

        LessonProgressDto progress =
                lessonProgressService.markVideoCompleted(progressId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Video marked as completed successfully.",
                        progress));
    }

    // Mark Quiz Passed
    @PutMapping("/{progressId}/quiz")
    public ResponseEntity<ApiResponse<LessonProgressDto>> markQuizPassed(
            @PathVariable Long progressId) {

        LessonProgressDto progress =
                lessonProgressService.markQuizPassed(progressId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz marked as passed successfully.",
                        progress));
    }

    // Get Lesson Progress
    @GetMapping("/{progressId}")
    public ResponseEntity<ApiResponse<LessonProgressDto>> getLessonProgress(
            @PathVariable Long progressId) {

        LessonProgressDto progress =
                lessonProgressService.getLessonProgress(progressId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lesson progress retrieved successfully.",
                        progress));
    }

    // Get Student Progress
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<LessonProgressDto>>> getStudentProgress(
            @PathVariable Long studentId) {

        List<LessonProgressDto> progressList =
                lessonProgressService.getStudentProgress(studentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student lesson progress retrieved successfully.",
                        progressList));
    }

    // Delete Lesson Progress
    @DeleteMapping("/{progressId}")
    public ResponseEntity<ApiResponse<Void>> deleteProgress(
            @PathVariable Long progressId) {

        lessonProgressService.deleteLessonProgress(progressId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lesson progress deleted successfully.",
                        null));
    }

}