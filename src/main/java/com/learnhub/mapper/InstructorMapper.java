package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.InstructorDto;
import com.learnhub.entity.Instructor;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    InstructorDto toDto(Instructor instructor);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Instructor toEntity(InstructorDto dto);

    List<InstructorDto> toDtoList(List<Instructor> instructors);

    List<Instructor> toEntityList(List<InstructorDto> dtos);

}