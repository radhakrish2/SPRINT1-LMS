package com.learnhub.dto;

import com.learnhub.entity.ProgressStatus;

import lombok.Data;

@Data
public class ProgressDto {

    private Long id;

    private Long studentId;

    private Long courseId;

    private String courseName;

    private Double percentage;

    private ProgressStatus status;

}