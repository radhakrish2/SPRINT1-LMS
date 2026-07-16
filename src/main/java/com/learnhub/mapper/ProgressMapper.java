package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.ProgressDto;
import com.learnhub.entity.Progress;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.title", target = "courseName")
    ProgressDto toDto(Progress progress);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Progress toEntity(ProgressDto dto);

    List<ProgressDto> toDtoList(List<Progress> progressList);

}