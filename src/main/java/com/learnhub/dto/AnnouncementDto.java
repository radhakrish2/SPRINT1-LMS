package com.learnhub.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AnnouncementDto {

    private Long id;

    private String title;

    private String message;

    private LocalDateTime createdAt;

    private LocalDateTime expiryDate;

    private boolean active;

    private Long courseId;

    private String courseName;

    private Long createdBy;

    private String createdByName;

}