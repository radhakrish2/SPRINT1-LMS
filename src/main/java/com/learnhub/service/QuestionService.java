package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.QuestionDto;
import com.learnhub.dto.QuestionRequestDto;

public interface QuestionService {

	QuestionDto createQuestion(Long quizId, QuestionRequestDto questionDto);
	
	QuestionDto updateQuestion(Long questionId, QuestionRequestDto questionDto);

	QuestionDto getQuestionById(Long questionId);

	List<QuestionDto> getAllQuestions();

	List<QuestionDto> getQuestionsByQuiz(Long quizId);

	void deleteQuestion(Long questionId);

}