package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.QuizDto;
import com.learnhub.entity.Quiz;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    QuizDto toDto(Quiz quiz);

    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "quizResults", ignore = true)
    Quiz toEntity(QuizDto dto);

    List<QuizDto> toDtoList(List<Quiz> quizzes);

    List<Quiz> toEntityList(List<QuizDto> dtos);

}