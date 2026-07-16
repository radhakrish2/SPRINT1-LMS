package com.learnhub.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnhub.dto.QuizResultDto;
import com.learnhub.entity.Quiz;
import com.learnhub.entity.QuizResult;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.QuizResultMapper;
import com.learnhub.repository.QuizRepository;
import com.learnhub.repository.QuizResultRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.QuizResultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizResultServiceImpl implements QuizResultService {

	private final QuizResultRepository quizResultRepository;
	private final QuizRepository quizRepository;
	private final UserRepository userRepository;
	private final QuizResultMapper quizResultMapper;

	/**
	 * Save Quiz Result
	 */
	@Override
	public QuizResultDto saveQuizResult(Long studentId, Long quizId, QuizResultDto dto) {

		User student = userRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + studentId));

		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		QuizResult quizResult = new QuizResult();

		quizResult.setStudent(student);
		quizResult.setQuiz(quiz);

		quizResult.setScore(dto.getScore());
		quizResult.setTotalQuestions(dto.getTotalQuestions());

		double percentage = 0;

		if (dto.getTotalQuestions() != null && dto.getTotalQuestions() > 0) {

			percentage = (dto.getScore() * 100.0) / dto.getTotalQuestions();
		}

		quizResult.setPercentage(percentage);

		QuizResult saved = quizResultRepository.save(quizResult);

		return quizResultMapper.toDto(saved);
	}

	

	/**
	 * Get All Quiz Results of a Student
	 */
	@Override
	@Transactional(readOnly = true)
	public List<QuizResultDto> getResultsByStudent(Long studentId) {

		if (!userRepository.existsById(studentId)) {
			throw new ResourceNotFoundException("Student not found with id : " + studentId);
		}

		return quizResultRepository.findByStudent_Id(studentId).stream().map(quizResultMapper::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * Get All Results of a Quiz
	 */
	@Override
	@Transactional(readOnly = true)
	public List<QuizResultDto> getResultsByQuiz(Long quizId) {

		if (!quizRepository.existsById(quizId)) {
			throw new ResourceNotFoundException("Quiz not found with id : " + quizId);
		}

		return quizResultRepository.findByQuiz_Id(quizId).stream().map(quizResultMapper::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * Get Student Result for a Quiz
	 */
	@Override
	@Transactional(readOnly = true)
	public QuizResultDto getStudentResult(Long studentId, Long quizId) {

		QuizResult result = quizResultRepository.findByStudent_IdAndQuiz_Id(studentId, quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz Result not found"));

		return quizResultMapper.toDto(result);
	}

	/**
	 * Update Quiz Result
	 */
	@Override
	public QuizResultDto updateQuizResult(Long resultId, QuizResultDto dto) {

		QuizResult result = quizResultRepository.findById(resultId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz Result not found with id : " + resultId));

		result.setScore(dto.getScore());
		result.setTotalQuestions(dto.getTotalQuestions());

		double percentage = 0;

		if (dto.getTotalQuestions() != null && dto.getTotalQuestions() > 0) {

			percentage = (dto.getScore() * 100.0) / dto.getTotalQuestions();
		}

		result.setPercentage(percentage);

		QuizResult updated = quizResultRepository.save(result);

		return quizResultMapper.toDto(updated);
	}

	/**
	 * Delete Quiz Result
	 */
	@Override
	public void deleteQuizResult(Long resultId) {

		QuizResult result = quizResultRepository.findById(resultId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz Result not found with id : " + resultId));

		quizResultRepository.delete(result);
	}

	/**
	 * Get Average Score of a Quiz
	 */
	@Override
	@Transactional(readOnly = true)
	public Double getAverageScore(Long quizId) {

		if (!quizRepository.existsById(quizId)) {
			throw new ResourceNotFoundException("Quiz not found with id : " + quizId);
		}

		Double average = quizResultRepository.getAverageScore(quizId);

		return average == null ? 0.0 : average;
	}

	/**
	 * Count Passed Students
	 */
	@Override
	@Transactional(readOnly = true)
	public Long getPassedStudentsCount(Long quizId) {

		if (!quizRepository.existsById(quizId)) {
			throw new ResourceNotFoundException("Quiz not found with id : " + quizId);
		}

		return quizResultRepository.countPassedStudents(quizId);
	}

	/**
	 * Count Failed Students
	 */
	@Override
	@Transactional(readOnly = true)
	public Long getFailedStudentsCount(Long quizId) {

		if (!quizRepository.existsById(quizId)) {
			throw new ResourceNotFoundException("Quiz not found with id : " + quizId);
		}

		return quizResultRepository.countFailedStudents(quizId);
	}
	
	@Override
	public QuizResultDto getQuizResult(Long resultId) {

	    QuizResult result = quizResultRepository.findById(resultId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Quiz Result not found with id : " + resultId));

	    return quizResultMapper.toDto(result);
	}

}
