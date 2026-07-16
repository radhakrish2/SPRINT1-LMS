package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.QuizResultDto;
public interface QuizResultService {

    QuizResultDto saveQuizResult(Long studentId,
                                 Long quizId,
                                 QuizResultDto quizResultDto);

    QuizResultDto getQuizResult(Long resultId);

    List<QuizResultDto> getResultsByStudent(Long studentId);

    List<QuizResultDto> getResultsByQuiz(Long quizId);

    QuizResultDto getStudentResult(Long studentId, Long quizId);

    QuizResultDto updateQuizResult(Long resultId,
                                   QuizResultDto quizResultDto);

    void deleteQuizResult(Long resultId);

    Double getAverageScore(Long quizId);

    Long getPassedStudentsCount(Long quizId);

    Long getFailedStudentsCount(Long quizId);
}