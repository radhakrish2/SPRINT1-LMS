package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.QuestionDto;
import com.learnhub.entity.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toDto(Question question);

    @Mapping(target = "quiz", ignore = true)
    @Mapping(target = "correctAnswer", ignore = true)
    Question toEntity(QuestionDto dto);

    List<QuestionDto> toDtoList(List<Question> questions);

    List<Question> toEntityList(List<QuestionDto> dtos);

}