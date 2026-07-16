package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.DashboardDto;
import com.learnhub.dto.EnrollmentDto;
import com.learnhub.dto.UserDto;
import com.learnhub.entity.Roles;
import com.learnhub.mapper.EnrollmentMapper;
import com.learnhub.mapper.UserMapper;
import com.learnhub.repository.CertificateRepository;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.EnrollmentRepository;
import com.learnhub.repository.InstructorRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CertificateRepository certificateRepository;

    private final UserMapper userMapper;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public DashboardDto getAdminDashboard() {

        return new DashboardDto(
                getTotalUsers(),
                getTotalStudents(),
                getTotalInstructors(),
                getTotalCourses(),
                getTotalEnrollments(),
                getTotalCertificates());
    }

    @Override
    public Long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public Long getTotalStudents() {

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Roles.STUDENT)
                .count();
    }

    @Override
    public Long getTotalInstructors() {
        return instructorRepository.count();
    }

    @Override
    public Long getTotalCourses() {
        return courseRepository.count();
    }

    @Override
    public Long getTotalEnrollments() {
        return enrollmentRepository.count();
    }

    @Override
    public Long getTotalCertificates() {
        return certificateRepository.count();
    }

    @Override
    public List<UserDto> getRecentUsers() {

        return userMapper.toDtoList(
                userRepository.findTop10ByOrderByIdDesc());
    }

    @Override
    public List<EnrollmentDto> getRecentEnrollments() {

        return enrollmentMapper.toDtoList(
                enrollmentRepository.findTop10ByOrderByEnrollmentDateDesc());
    }

    @Override
    public List<Object[]> getCourseEnrollmentStatistics() {

        return enrollmentRepository.getCourseEnrollmentStatistics();
    }

}