package com.learnhub.dto;

import lombok.Data;

@Data
public class CourseDto {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private String thumbnail;

    private Integer duration;

    private String level;

    // Required for Save/Update
    private Long instructorId;

    private Long categoryId;

    // For Display
    private String instructorName;

    private String category;

}