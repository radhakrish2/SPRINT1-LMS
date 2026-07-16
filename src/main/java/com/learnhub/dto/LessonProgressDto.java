package com.learnhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonProgressDto {

    private Long id;

    private Long studentId;

    private String studentName;

    private Long lessonId;

    private String lessonTitle;

    private boolean videoCompleted;

    private boolean quizPassed;

}