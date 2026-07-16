package com.learnhub.dto;


import java.time.LocalDate;

import com.learnhub.entity.EnrollmentStatus;

import lombok.Data;

@Data
public class EnrollmentDto {

    private Long id;

    private Long studentId;

    private Long courseId;

    private String courseName;

    private LocalDate enrollmentDate;

    private EnrollmentStatus status;

    private Double progress;

    private Double score;

}