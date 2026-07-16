package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.LessonProgressDto;

public interface LessonProgressService {

	LessonProgressDto createLessonProgress(Long studentId, Long lessonId);

	LessonProgressDto markVideoCompleted(Long progressId);

	LessonProgressDto markQuizPassed(Long progressId);

	LessonProgressDto getLessonProgress(Long progressId);

	List<LessonProgressDto> getStudentProgress(Long studentId);

	List<LessonProgressDto> getLessonProgressByLesson(Long lessonId);

	void deleteLessonProgress(Long progressId);

}