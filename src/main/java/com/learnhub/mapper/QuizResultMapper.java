package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.QuizResultDto;
import com.learnhub.entity.QuizResult;

@Mapper(componentModel = "spring")
public interface QuizResultMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "quiz.title", target = "quizTitle")
    QuizResultDto toDto(QuizResult quizResult);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    QuizResult toEntity(QuizResultDto dto);

    List<QuizResultDto> toDtoList(List<QuizResult> results);

}