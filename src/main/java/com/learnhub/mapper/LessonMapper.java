package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.LessonDto;
import com.learnhub.entity.Lesson;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(source = "course.id", target = "courseId")
    LessonDto toDto(Lesson lesson);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "quizzes", ignore = true)
    @Mapping(target = "lessonProgress", ignore = true)
    Lesson toEntity(LessonDto dto);

    List<LessonDto> toDtoList(List<Lesson> lessons);

}