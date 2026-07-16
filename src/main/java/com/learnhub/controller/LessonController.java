package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.LessonDto;
import com.learnhub.service.LessonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LessonController {

    private final LessonService lessonService;

    // Create Lesson
    @PostMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<LessonDto>> createLesson(
            @PathVariable Long courseId,
            @Valid @RequestBody LessonDto lessonDto) {

        LessonDto lesson = lessonService.createLesson(courseId, lessonDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Lesson created successfully.",
                        lesson));
    }

    // Update Lesson
    @PutMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<LessonDto>> updateLesson(
            @PathVariable Long lessonId,
            @Valid @RequestBody LessonDto lessonDto) {

        LessonDto lesson = lessonService.updateLesson(lessonId, lessonDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lesson updated successfully.",
                        lesson));
    }

    // Get Lesson By Id
    @GetMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<LessonDto>> getLessonById(
            @PathVariable Long lessonId) {

        LessonDto lesson = lessonService.getLessonById(lessonId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lesson retrieved successfully.",
                        lesson));
    }

    // Get Lessons By Course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<LessonDto>>> getLessonsByCourse(
            @PathVariable Long courseId) {

        List<LessonDto> lessons =
                lessonService.getLessonsByCourse(courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lessons retrieved successfully.",
                        lessons));
    }

    // Delete Lesson
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<Void>> deleteLesson(
            @PathVariable Long lessonId) {

        lessonService.deleteLesson(lessonId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lesson deleted successfully.",
                        null));
    }

}