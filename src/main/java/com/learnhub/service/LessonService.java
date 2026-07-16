package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.LessonDto;

public interface LessonService {


    LessonDto createLesson(Long courseId, LessonDto lessonDto);


    LessonDto updateLesson(Long lessonId, LessonDto lessonDto);


    LessonDto getLessonById(Long lessonId);


    List<LessonDto> getLessonsByCourse(Long courseId);


    void deleteLesson(Long lessonId);

}