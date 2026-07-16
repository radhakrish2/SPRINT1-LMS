package com.learnhub.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.EnrollmentDto;
import com.learnhub.entity.Course;
import com.learnhub.entity.Enrollment;
import com.learnhub.entity.EnrollmentStatus;
import com.learnhub.entity.Student;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.EnrollmentMapper;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.EnrollmentRepository;
import com.learnhub.repository.StudentRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentDto enrollCourse(Long studentId,
                                      Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : "
                                        + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : "
                                        + courseId));

        if (enrollmentRepository.existsByStudentIdAndCourseId(
                studentId,
                courseId)) {

            throw new IllegalArgumentException(
                    "Student is already enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollment.setStatus(EnrollmentStatus.ENROLLED);

        enrollment.setProgress(0.0);

        enrollment.setScore(0.0);

        enrollment.setCertificateIssued(false);

        enrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toDto(enrollment);
    }

    @Override
    public EnrollmentDto getEnrollment(Long studentId,
                                       Long courseId) {

        Enrollment enrollment =
                enrollmentRepository
                        .findByStudentIdAndCourseId(
                                studentId,
                                courseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found."));

        return enrollmentMapper.toDto(enrollment);
    }



    @Override
    public List<EnrollmentDto> getEnrollmentsByStudent(Long studentId) {

        // Validate student
        userRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(studentId);

        return enrollmentMapper.toDtoList(enrollments);
    }

    @Override
    public List<EnrollmentDto> getEnrollmentsByCourse(Long courseId) {

        // Validate course
        courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : " + courseId));

        List<Enrollment> enrollments =
                enrollmentRepository.findByCourseId(courseId);

        return enrollmentMapper.toDtoList(enrollments);
    }

    @Override
    public void cancelEnrollment(Long studentId, Long courseId) {

        Enrollment enrollment =
                enrollmentRepository
                        .findByStudentIdAndCourseId(studentId, courseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found."));

        // Option 1 (Recommended): Keep history
        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollmentRepository.save(enrollment);

        /*
        // Option 2: Permanently delete the enrollment
        enrollmentRepository.delete(enrollment);
        */
    }

}