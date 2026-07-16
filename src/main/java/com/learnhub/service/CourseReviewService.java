package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.CourseReviewDto;

public interface CourseReviewService {

	CourseReviewDto addReview(Long studentId, Long courseId, CourseReviewDto reviewDto);

	CourseReviewDto updateReview(Long reviewId, CourseReviewDto reviewDto);

	CourseReviewDto getReviewById(Long reviewId);

	List<CourseReviewDto> getReviewsByCourse(Long courseId);

	List<CourseReviewDto> getReviewsByStudent(Long studentId);

	Double getAverageRating(Long courseId);

	void deleteReview(Long reviewId);

}