package com.learnhub.dto;

import lombok.Data;

@Data
public class LessonDto {

    private Long id;

    private String title;

    private String description;

    private String videoUrl;

    private String pdfUrl;

    private Integer duration;

    private Integer lessonOrder;

    // Required
    private Long courseId;
}