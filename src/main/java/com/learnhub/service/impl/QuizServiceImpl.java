package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.QuestionDto;
import com.learnhub.dto.QuizDto;
import com.learnhub.dto.QuizResultDto;
import com.learnhub.entity.Course;
import com.learnhub.entity.Lesson;
import com.learnhub.entity.LessonProgress;
import com.learnhub.entity.Progress;
import com.learnhub.entity.ProgressStatus;
import com.learnhub.entity.Question;
import com.learnhub.entity.Quiz;
import com.learnhub.entity.QuizResult;
import com.learnhub.entity.StudentAnswer;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.QuestionMapper;
import com.learnhub.mapper.QuizMapper;
import com.learnhub.repository.LessonProgressRepository;
import com.learnhub.repository.LessonRepository;
import com.learnhub.repository.ProgressRepository;
import com.learnhub.repository.QuestionRepository;
import com.learnhub.repository.QuizRepository;
import com.learnhub.repository.QuizResultRepository;
import com.learnhub.repository.StudentAnswerRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.QuizService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;
	private final LessonRepository lessonRepository;
	private final QuestionRepository questionRepository;
	private final QuizResultRepository quizResultRepository;
	private final StudentAnswerRepository studentAnswerRepository;
	private final UserRepository userRepository;
	private final LessonProgressRepository lessonProgressRepository;
	private final ProgressRepository progressRepository;

	private final QuizMapper quizMapper;
	private final QuestionMapper questionMapper;

	@Override
	public QuizDto createQuiz(Long lessonId, QuizDto quizDto) {

		Lesson lesson = lessonRepository.findById(lessonId)
				.orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id : " + lessonId));

		Quiz quiz = quizMapper.toEntity(quizDto);

		quiz.setLesson(lesson);

		quiz = quizRepository.save(quiz);

		return quizMapper.toDto(quiz);
	}

	@Override
	public QuizDto updateQuiz(Long quizId, QuizDto quizDto) {

		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		quiz.setTitle(quizDto.getTitle());
		quiz.setTotalMarks(quizDto.getTotalMarks());
		quiz.setPassingMarks(quizDto.getPassingMarks());
		quiz.setDurationInMinutes(quizDto.getDurationInMinutes());

		quiz = quizRepository.save(quiz);

		return quizMapper.toDto(quiz);
	}

	@Override
	public QuizDto getQuizById(Long quizId) {

		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		return quizMapper.toDto(quiz);
	}

	@Override
	public List<QuizDto> getQuizzesByLesson(Long lessonId) {

		return quizMapper.toDtoList(quizRepository.findByLessonId(lessonId));
	}

	@Override
	public void deleteQuiz(Long quizId) {

		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		quizRepository.delete(quiz);
	}

	// ===========================
	// Part 2
	// ===========================

	@Override
	public QuestionDto addQuestion(Long quizId, QuestionDto questionDto) {

		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		Question question = questionMapper.toEntity(questionDto);

		question.setQuiz(quiz);

		question = questionRepository.save(question);

		return questionMapper.toDto(question);
	}

	@Override
	public List<QuestionDto> getQuestionsByQuiz(Long quizId) {

		// Validate quiz exists
		quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		return questionMapper.toDtoList(questionRepository.findByQuizId(quizId));
	}

	// ===========================
	// Part 3
	// ===========================

	@Override
	@Transactional
	public QuizResultDto submitQuiz(Long quizId, Long studentId, List<String> answers) {

		// Validate Quiz
		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id : " + quizId));

		// Validate Student
		User student = userRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + studentId));

		// Get Questions
		List<Question> questions = questionRepository.findByQuizId(quizId);

		if (questions.isEmpty()) {
			throw new ResourceNotFoundException("No questions found for this quiz.");
		}

		if (answers.size() != questions.size()) {
			throw new IllegalArgumentException("Number of submitted answers does not match total questions.");
		}

		int score = 0;

		// Evaluate Answers
		for (int i = 0; i < questions.size(); i++) {

			Question question = questions.get(i);

			String submittedAnswer = answers.get(i);

			// Save Student Answer
			StudentAnswer studentAnswer = new StudentAnswer();
			studentAnswer.setStudent(student);
			studentAnswer.setQuestion(question);
			studentAnswer.setSelectedAnswer(submittedAnswer);

			studentAnswerRepository.save(studentAnswer);

			// Compare Answer
			if (question.getCorrectAnswer() != null && question.getCorrectAnswer().equalsIgnoreCase(submittedAnswer)) {

				score++;
			}
		}

		int totalQuestions = questions.size();

		double percentage = ((double) score / totalQuestions) * 100;

		// Save Quiz Result
		QuizResult quizResult = new QuizResult();

		quizResult.setStudent(student);
		quizResult.setQuiz(quiz);
		quizResult.setScore(score);
		quizResult.setTotalQuestions(totalQuestions);
		quizResult.setPercentage(percentage);

		quizResultRepository.save(quizResult);

		// Update Lesson Progress
		Lesson lesson = quiz.getLesson();

		LessonProgress lessonProgress = lessonProgressRepository.findByUserIdAndLessonId(studentId, lesson.getId())
				.orElse(new LessonProgress());

		lessonProgress.setUser(student);
		lessonProgress.setLesson(lesson);

		if (percentage >= quiz.getPassingMarks()) {
			lessonProgress.setQuizPassed(true);
		} else {
			lessonProgress.setQuizPassed(false);
		}

		lessonProgressRepository.save(lessonProgress);

		// Update Overall Course Progress
		Course course = lesson.getCourse();

		Progress progress = progressRepository.findByStudentIdAndCourseId(studentId, course.getId())
				.orElse(new Progress());

		progress.setStudent(student);
		progress.setCourse(course);

		List<Lesson> lessons = lessonRepository.findByCourseId(course.getId());

		long completedLessons = lessonProgressRepository.countByUserIdAndLessonCourseIdAndQuizPassedTrue(studentId,
				course.getId());

		double overallProgress = ((double) completedLessons / lessons.size()) * 100;

		progress.setPercentage(overallProgress);

		if (overallProgress == 100) {
			progress.setStatus(ProgressStatus.COMPLETED);
		} else {
			progress.setStatus(ProgressStatus.IN_PROGRESS);
		}

		progressRepository.save(progress);

		// Prepare Response
		QuizResultDto dto = new QuizResultDto();

		dto.setId(quizResult.getId());
		dto.setScore(score);
		dto.setTotalQuestions(totalQuestions);
		dto.setPercentage(percentage);
		dto.setQuizTitle(quiz.getTitle());

		return dto;
	}

	private void updateLessonProgress(User student, Quiz quiz, double percentage) {

		Lesson lesson = quiz.getLesson();

		LessonProgress lessonProgress = lessonProgressRepository
				.findByUserIdAndLessonId(student.getId(), lesson.getId()).orElse(new LessonProgress());

		lessonProgress.setUser(student);
		lessonProgress.setLesson(lesson);

		lessonProgress.setQuizPassed(percentage >= quiz.getPassingMarks());

		lessonProgressRepository.save(lessonProgress);
	}

	private void updateCourseProgress(User student, Course course) {

		Progress progress = progressRepository.findByStudentIdAndCourseId(student.getId(), course.getId())
				.orElse(new Progress());

		progress.setStudent(student);
		progress.setCourse(course);

		List<Lesson> lessons = lessonRepository.findByCourseId(course.getId());

		long completedLessons = lessonProgressRepository
				.countByUserIdAndLessonCourseIdAndQuizPassedTrue(student.getId(), course.getId());

		double percentage = 0;

		if (!lessons.isEmpty()) {

			percentage = (completedLessons * 100.0) / lessons.size();
		}

		progress.setPercentage(percentage);

		if (percentage >= 100) {

			progress.setStatus(ProgressStatus.COMPLETED);

		} else if (percentage > 0) {

			progress.setStatus(ProgressStatus.IN_PROGRESS);

		} else {

			progress.setStatus(ProgressStatus.NOT_STARTED);
		}

		progressRepository.save(progress);
	}
}