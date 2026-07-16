package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.QuestionDto;
import com.learnhub.dto.QuestionRequestDto;
import com.learnhub.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;

    // Create Question
    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<ApiResponse<QuestionDto>> createQuestion(
            @PathVariable Long quizId,
            @Valid @RequestBody QuestionRequestDto questionDto) {

        QuestionDto question = questionService.createQuestion(quizId, questionDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Question created successfully.",
                        question));
    }

    // Update Question
    @PutMapping("/{questionId}")
    public ResponseEntity<ApiResponse<QuestionDto>> updateQuestion(
            @PathVariable Long questionId,
            @Valid @RequestBody QuestionRequestDto questionDto) {

        QuestionDto question =
                questionService.updateQuestion(questionId, questionDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Question updated successfully.",
                        question));
    }

    // Get Question By Id
    @GetMapping("/{questionId}")
    public ResponseEntity<ApiResponse<QuestionDto>> getQuestionById(
            @PathVariable Long questionId) {

        QuestionDto question =
                questionService.getQuestionById(questionId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Question retrieved successfully.",
                        question));
    }

    // Get All Questions
    @GetMapping
    public ResponseEntity<ApiResponse<List<QuestionDto>>> getAllQuestions() {

        List<QuestionDto> questions =
                questionService.getAllQuestions();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Questions retrieved successfully.",
                        questions));
    }

    // Get Questions By Quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<ApiResponse<List<QuestionDto>>> getQuestionsByQuiz(
            @PathVariable Long quizId) {

        List<QuestionDto> questions =
                questionService.getQuestionsByQuiz(quizId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quiz questions retrieved successfully.",
                        questions));
    }

    // Delete Question
    @DeleteMapping("/{questionId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(
            @PathVariable Long questionId) {

        questionService.deleteQuestion(questionId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Question deleted successfully.",
                        null));
    }

}