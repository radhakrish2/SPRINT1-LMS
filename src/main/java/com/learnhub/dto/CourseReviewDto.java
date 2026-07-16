package com.learnhub.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CourseReviewDto {

    private Long id;

    private Integer rating;

    private String review;

    private LocalDateTime reviewDate;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;

}