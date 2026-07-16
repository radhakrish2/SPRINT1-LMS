package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.EnrollmentDto;

public interface EnrollmentService {

    EnrollmentDto enrollCourse(Long studentId, Long courseId);

    List<EnrollmentDto> getEnrollmentsByStudent(Long studentId);

    List<EnrollmentDto> getEnrollmentsByCourse(Long courseId);

    EnrollmentDto getEnrollment(Long studentId, Long courseId);

    void cancelEnrollment(Long studentId, Long courseId);

}