package com.learnhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.DashboardDto;
import com.learnhub.dto.EnrollmentDto;
import com.learnhub.dto.UserDto;
import com.learnhub.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    // Complete Dashboard
    @GetMapping
    public ResponseEntity<ApiResponse<DashboardDto>> getDashboard() {

        DashboardDto dashboard = dashboardService.getAdminDashboard();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dashboard loaded successfully.",
                        dashboard));
    }

    // ================= Statistics =================

    @GetMapping("/users/count")
    public ResponseEntity<ApiResponse<Long>> getTotalUsers() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Total users retrieved successfully.",
                        dashboardService.getTotalUsers()));
    }

    @GetMapping("/students/count")
    public ResponseEntity<ApiResponse<Long>> getTotalStudents() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Total students retrieved successfully.",
                        dashboardService.getTotalStudents()));
    }

    @GetMapping("/instructors/count")
    public ResponseEntity<ApiResponse<Long>> getTotalInstructors() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Total instructors retrieved successfully.",
                        dashboardService.getTotalInstructors()));
    }

    @GetMapping("/courses/count")
    public ResponseEntity<ApiResponse<Long>> getTotalCourses() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Total courses retrieved successfully.",
                        dashboardService.getTotalCourses()));
    }

    @GetMapping("/enrollments/count")
    public ResponseEntity<ApiResponse<Long>> getTotalEnrollments() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Total enrollments retrieved successfully.",
                        dashboardService.getTotalEnrollments()));
    }

    @GetMapping("/certificates/count")
    public ResponseEntity<ApiResponse<Long>> getTotalCertificates() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Total certificates retrieved successfully.",
                        dashboardService.getTotalCertificates()));
    }

    // ================= Recent Users =================

    @GetMapping("/recent-users")
    public ResponseEntity<ApiResponse<List<UserDto>>> getRecentUsers() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Recent users retrieved successfully.",
                        dashboardService.getRecentUsers()));
    }

    // ================= Recent Enrollments =================

    @GetMapping("/recent-enrollments")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getRecentEnrollments() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Recent enrollments retrieved successfully.",
                        dashboardService.getRecentEnrollments()));
    }

    // ================= Course Statistics =================

    @GetMapping("/course-statistics")
    public ResponseEntity<ApiResponse<List<Object[]>>> getCourseEnrollmentStatistics() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course enrollment statistics retrieved successfully.",
                        dashboardService.getCourseEnrollmentStatistics()));
    }

}