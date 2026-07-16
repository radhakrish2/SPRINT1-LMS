package com.learnhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.LessonProgress;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

	List<LessonProgress> findByUserId(Long userId);

	List<LessonProgress> findByLessonId(Long lessonId);

	Optional<LessonProgress> findByUserIdAndLessonId(Long userId, Long lessonId);

	long countByUserIdAndLessonCourseIdAndQuizPassedTrue(Long userId, Long courseId);
}