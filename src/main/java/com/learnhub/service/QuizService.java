package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.QuestionDto;
import com.learnhub.dto.QuizDto;
import com.learnhub.dto.QuizResultDto;

public interface QuizService {

    QuizDto createQuiz(Long lessonId, QuizDto quizDto);

    QuizDto updateQuiz(Long quizId, QuizDto quizDto);

    QuizDto getQuizById(Long quizId);

    List<QuizDto> getQuizzesByLesson(Long lessonId);

    void deleteQuiz(Long quizId);

    QuestionDto addQuestion(Long quizId, QuestionDto questionDto);

    List<QuestionDto> getQuestionsByQuiz(Long quizId);

    QuizResultDto submitQuiz(Long quizId, Long studentId, List<String> answers);

}