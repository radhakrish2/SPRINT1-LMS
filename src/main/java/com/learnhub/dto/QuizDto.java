package com.learnhub.dto;

import lombok.Data;

@Data
public class QuizDto {

    private Long id;

    private String title;

    private Integer totalMarks;

    private Integer passingMarks;

    private Integer durationInMinutes;

    // Required
    private Long lessonId;
}