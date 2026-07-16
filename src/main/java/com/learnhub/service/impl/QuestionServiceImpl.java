package com.learnhub.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnhub.dto.QuestionDto;
import com.learnhub.dto.QuestionRequestDto;
import com.learnhub.entity.Question;
import com.learnhub.entity.Quiz;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.repository.QuestionRepository;
import com.learnhub.repository.QuizRepository;
import com.learnhub.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {

	private final QuestionRepository questionRepository;

	private final QuizRepository quizRepository;

	@Override
	public QuestionDto createQuestion(Long quizId, QuestionRequestDto questionDto) {

		Quiz quiz = quizRepository.findById(quizId)

				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		Question question = new Question();

		question.setQuestionText(questionDto.getQuestionText());

		question.setOptionA(questionDto.getOptionA());

		question.setOptionB(questionDto.getOptionB());

		question.setOptionC(questionDto.getOptionC());

		question.setOptionD(questionDto.getOptionD());

		question.setCorrectAnswer(questionDto.getCorrectAnswer());

		question.setQuiz(quiz);

		Question savedQuestion = questionRepository.save(question);

		return mapToDto(savedQuestion);
	}

	@Override
	public QuestionDto updateQuestion(Long questionId, QuestionRequestDto questionDto) {

		Question question = questionRepository.findById(questionId)

				.orElseThrow(() -> new ResourceNotFoundException("Question not found with id : " + questionId));

		question.setQuestionText(questionDto.getQuestionText());

		question.setOptionA(questionDto.getOptionA());

		question.setOptionB(questionDto.getOptionB());

		question.setOptionC(questionDto.getOptionC());

		question.setOptionD(questionDto.getOptionD());

		question.setCorrectAnswer(questionDto.getCorrectAnswer());

		Question updatedQuestion = questionRepository.save(question);

		return mapToDto(updatedQuestion);
	}

	@Override
	@Transactional(readOnly = true)
	public QuestionDto getQuestionById(Long questionId) {

		Question question = questionRepository.findById(questionId)

				.orElseThrow(() -> new ResourceNotFoundException("Question not found with id : " + questionId));

		return mapToDto(question);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> getAllQuestions() {

		return questionRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> getQuestionsByQuiz(Long quizId) {

		if (!quizRepository.existsById(quizId)) {

			throw new ResourceNotFoundException("Quiz not found with id : " + quizId);
		}

		return questionRepository.findByQuizId(quizId)

				.stream()

				.map(this::mapToDto)

				.collect(Collectors.toList());
	}

	@Override
	public void deleteQuestion(Long questionId) {

		Question question = questionRepository.findById(questionId)

				.orElseThrow(() -> new ResourceNotFoundException("Question not found with id : " + questionId));

		questionRepository.delete(question);
	}

	private QuestionDto mapToDto(Question question) {

		QuestionDto dto = new QuestionDto();

		dto.setId(question.getId());

		dto.setQuestionText(question.getQuestionText());

		dto.setOptionA(question.getOptionA());

		dto.setOptionB(question.getOptionB());

		dto.setOptionC(question.getOptionC());

		dto.setOptionD(question.getOptionD());

		return dto;
	}

}