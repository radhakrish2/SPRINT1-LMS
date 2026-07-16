package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.QuizResultDto;
import com.learnhub.service.QuizResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quiz-results")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizResultController {

    private final QuizResultService quizResultService;

    // Save Quiz Result
    @PostMapping("/student/{studentId}/quiz/{quizId}")
    public ResponseEntity<ApiResponse<QuizResultDto>> saveQuizResult(
            @PathVariable Long studentId,
            @PathVariable Long quizId,
            @RequestBody QuizResultDto quizResultDto) {

        QuizResultDto result = quizResultService.saveQuizResult(
                studentId,
                quizId,
                quizResultDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Quiz result saved successfully.",
                        result));
    }

    // Get Quiz Result By Id
    @GetMapping("/{resultId}")
    public ResponseEntity<ApiResponse<QuizResultDto>> getQuizResult(
            @PathVariable Long resultId) {

        QuizResultDto result =
                quizResultService.getQuizResult(resultId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz result retrieved successfully.",
                        result));
    }

    // Get Results By Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<QuizResultDto>>> getResultsByStudent(
            @PathVariable Long studentId) {

        List<QuizResultDto> results =
                quizResultService.getResultsByStudent(studentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student quiz results retrieved successfully.",
                        results));
    }

    // Get Results By Quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<ApiResponse<List<QuizResultDto>>> getResultsByQuiz(
            @PathVariable Long quizId) {

        List<QuizResultDto> results =
                quizResultService.getResultsByQuiz(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz results retrieved successfully.",
                        results));
    }

    // Get Student Result For Particular Quiz
    @GetMapping("/student/{studentId}/quiz/{quizId}")
    public ResponseEntity<ApiResponse<QuizResultDto>> getStudentResult(
            @PathVariable Long studentId,
            @PathVariable Long quizId) {

        QuizResultDto result =
                quizResultService.getStudentResult(studentId, quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student quiz result retrieved successfully.",
                        result));
    }

    // Update Quiz Result
    @PutMapping("/{resultId}")
    public ResponseEntity<ApiResponse<QuizResultDto>> updateQuizResult(
            @PathVariable Long resultId,
            @RequestBody QuizResultDto quizResultDto) {

        QuizResultDto result =
                quizResultService.updateQuizResult(resultId, quizResultDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz result updated successfully.",
                        result));
    }

    // Delete Quiz Result
    @DeleteMapping("/{resultId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuizResult(
            @PathVariable Long resultId) {

        quizResultService.deleteQuizResult(resultId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz result deleted successfully.",
                        null));
    }

    // Get Average Score
    @GetMapping("/quiz/{quizId}/average-score")
    public ResponseEntity<ApiResponse<Double>> getAverageScore(
            @PathVariable Long quizId) {

        Double averageScore =
                quizResultService.getAverageScore(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Average score retrieved successfully.",
                        averageScore));
    }

    // Get Passed Students Count
    @GetMapping("/quiz/{quizId}/passed-count")
    public ResponseEntity<ApiResponse<Long>> getPassedStudentsCount(
            @PathVariable Long quizId) {

        Long passedCount =
                quizResultService.getPassedStudentsCount(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Passed students count retrieved successfully.",
                        passedCount));
    }

    // Get Failed Students Count
    @GetMapping("/quiz/{quizId}/failed-count")
    public ResponseEntity<ApiResponse<Long>> getFailedStudentsCount(
            @PathVariable Long quizId) {

        Long failedCount =
                quizResultService.getFailedStudentsCount(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Failed students count retrieved successfully.",
                        failedCount));
    }

}