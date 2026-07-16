package com.learnhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.CourseReviewDto;
import com.learnhub.entity.Course;
import com.learnhub.entity.CourseReview;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.CourseReviewMapper;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.CourseReviewRepository;
import com.learnhub.repository.EnrollmentRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.CourseReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseReviewServiceImpl implements CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseReviewMapper courseReviewMapper;

    @Override
    public CourseReviewDto addReview(Long studentId,
                                     Long courseId,
                                     CourseReviewDto reviewDto) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : " + courseId));

        // Student must be enrolled
        if (!enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new IllegalStateException(
                    "Student is not enrolled in this course.");
        }

        // Only one review per student per course
        if (courseReviewRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new IllegalStateException(
                    "You have already reviewed this course.");
        }

        // Rating validation
        if (reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5.");
        }

        CourseReview review = courseReviewMapper.toEntity(reviewDto);

        review.setStudent(student);
        review.setCourse(course);
        review.setReviewDate(LocalDateTime.now());

        review = courseReviewRepository.save(review);

        return courseReviewMapper.toDto(review);
    }

    @Override
    public CourseReviewDto updateReview(Long reviewId,
                                        CourseReviewDto reviewDto) {

        CourseReview review = courseReviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review not found with id : " + reviewId));

        if (reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5.");
        }

        review.setRating(reviewDto.getRating());
        review.setReview(reviewDto.getReview());
        review.setReviewDate(LocalDateTime.now());

        review = courseReviewRepository.save(review);

        return courseReviewMapper.toDto(review);
    }

    @Override
    public CourseReviewDto getReviewById(Long reviewId) {

        CourseReview review = courseReviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review not found with id : " + reviewId));

        return courseReviewMapper.toDto(review);
    }

    @Override
    public List<CourseReviewDto> getReviewsByCourse(Long courseId) {

        courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : " + courseId));

        return courseReviewMapper.toDtoList(
                courseReviewRepository.findByCourseId(courseId));
    }

    @Override
    public List<CourseReviewDto> getReviewsByStudent(Long studentId) {

        userRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        return courseReviewMapper.toDtoList(
                courseReviewRepository.findByStudentId(studentId));
    }

    @Override
    public Double getAverageRating(Long courseId) {

        List<CourseReview> reviews =
                courseReviewRepository.findByCourseId(courseId);

        if (reviews.isEmpty()) {
            return 0.0;
        }

        double total = reviews.stream()
                .mapToInt(CourseReview::getRating)
                .sum();

        return total / reviews.size();
    }

    @Override
    public void deleteReview(Long reviewId) {

        CourseReview review = courseReviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review not found with id : " + reviewId));

        courseReviewRepository.delete(review);
    }
}