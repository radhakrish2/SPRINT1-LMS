package com.learnhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDto {

    private Long totalUsers;

    private Long totalStudents;

    private Long totalInstructors;

    private Long totalCourses;

    private Long totalEnrollments;

    private Long totalCertificates;

}