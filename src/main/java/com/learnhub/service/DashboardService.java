package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.DashboardDto;
import com.learnhub.dto.EnrollmentDto;
import com.learnhub.dto.UserDto;

public interface DashboardService {

    // Complete Dashboard
    DashboardDto getAdminDashboard();

    // Statistics
    Long getTotalUsers();

    Long getTotalStudents();

    Long getTotalInstructors();

    Long getTotalCourses();

    Long getTotalEnrollments();

    Long getTotalCertificates();

    // Recent Activities
    List<UserDto> getRecentUsers();

    List<EnrollmentDto> getRecentEnrollments();

    // Dashboard Charts
    List<Object[]> getCourseEnrollmentStatistics();

}