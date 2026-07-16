package com.learnhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.CourseReview;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

	List<CourseReview> findByCourseId(Long courseId);

	List<CourseReview> findByStudentId(Long studentId);

	Optional<CourseReview> findByStudentIdAndCourseId(Long studentId, Long courseId);

	boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

}