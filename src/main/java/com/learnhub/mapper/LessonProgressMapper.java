package com.learnhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.LessonProgressDto;
import com.learnhub.entity.LessonProgress;

@Mapper(componentModel = "spring")
public interface LessonProgressMapper {

    @Mapping(source = "user.id", target = "studentId")
    @Mapping(source = "user.name", target = "studentName")
    @Mapping(source = "lesson.id", target = "lessonId")
    @Mapping(source = "lesson.title", target = "lessonTitle")
    LessonProgressDto toDto(LessonProgress lessonProgress);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    LessonProgress toEntity(LessonProgressDto lessonProgressDto);

}