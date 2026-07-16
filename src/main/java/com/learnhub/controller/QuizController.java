package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.QuestionDto;
import com.learnhub.dto.QuizDto;
import com.learnhub.dto.QuizResultDto;
import com.learnhub.service.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;

    // Create Quiz for Lesson
    @PostMapping("/lesson/{lessonId}")
    public ResponseEntity<ApiResponse<QuizDto>> createQuiz(
            @PathVariable Long lessonId,
            @Valid @RequestBody QuizDto quizDto) {

        QuizDto quiz = quizService.createQuiz(lessonId, quizDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Quiz created successfully.",
                        quiz));
    }

    // Update Quiz
    @PutMapping("/{quizId}")
    public ResponseEntity<ApiResponse<QuizDto>> updateQuiz(
            @PathVariable Long quizId,
            @Valid @RequestBody QuizDto quizDto) {

        QuizDto quiz = quizService.updateQuiz(quizId, quizDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz updated successfully.",
                        quiz));
    }

    // Get Quiz By Id
    @GetMapping("/{quizId}")
    public ResponseEntity<ApiResponse<QuizDto>> getQuizById(
            @PathVariable Long quizId) {

        QuizDto quiz = quizService.getQuizById(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz retrieved successfully.",
                        quiz));
    }

    // Get Quizzes By Lesson
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<ApiResponse<List<QuizDto>>> getQuizzesByLesson(
            @PathVariable Long lessonId) {

        List<QuizDto> quizzes = quizService.getQuizzesByLesson(lessonId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quizzes retrieved successfully.",
                        quizzes));
    }

    // Delete Quiz
    @DeleteMapping("/{quizId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuiz(
            @PathVariable Long quizId) {

        quizService.deleteQuiz(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz deleted successfully.",
                        null));
    }

    // Add Question
    @PostMapping("/{quizId}/questions")
    public ResponseEntity<ApiResponse<QuestionDto>> addQuestion(
            @PathVariable Long quizId,
            @Valid @RequestBody QuestionDto questionDto) {

        QuestionDto question = quizService.addQuestion(quizId, questionDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Question added successfully.",
                        question));
    }

    // Get Questions of Quiz
    @GetMapping("/{quizId}/questions")
    public ResponseEntity<ApiResponse<List<QuestionDto>>> getQuestionsByQuiz(
            @PathVariable Long quizId) {

        List<QuestionDto> questions =
                quizService.getQuestionsByQuiz(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz questions retrieved successfully.",
                        questions));
    }

    // Submit Quiz
    @PostMapping("/{quizId}/submit/{studentId}")
    public ResponseEntity<ApiResponse<QuizResultDto>> submitQuiz(
            @PathVariable Long quizId,
            @PathVariable Long studentId,
            @RequestBody List<String> answers) {

        QuizResultDto result =
                quizService.submitQuiz(quizId, studentId, answers);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz submitted successfully.",
                        result));
    }

}