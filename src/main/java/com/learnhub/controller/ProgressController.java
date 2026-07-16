package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.ProgressDto;
import com.learnhub.service.ProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProgressController {

    private final ProgressService progressService;

    // Create Progress
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<ProgressDto>> createProgress(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        ProgressDto progress = progressService.createProgress(studentId, courseId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Progress created successfully.",
                        progress));
    }

    // Update Progress
    @PutMapping("/{progressId}")
    public ResponseEntity<ApiResponse<ProgressDto>> updateProgress(
            @PathVariable Long progressId,
            @RequestBody ProgressDto progressDto) {

        ProgressDto progress =
                progressService.updateProgress(progressId, progressDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Progress updated successfully.",
                        progress));
    }

    // Get Progress by Student and Course
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<ProgressDto>> getProgress(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        ProgressDto progress =
                progressService.getProgress(studentId, courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Progress retrieved successfully.",
                        progress));
    }

    // Get All Progress of Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<ProgressDto>>> getStudentProgress(
            @PathVariable Long studentId) {

        List<ProgressDto> progressList =
                progressService.getStudentProgress(studentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student progress retrieved successfully.",
                        progressList));
    }

    // Delete Progress
    @DeleteMapping("/{progressId}")
    public ResponseEntity<ApiResponse<Void>> deleteProgress(
            @PathVariable Long progressId) {

        progressService.deleteProgress(progressId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Progress deleted successfully.",
                        null));
    }

}