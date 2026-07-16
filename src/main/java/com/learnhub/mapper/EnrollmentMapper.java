package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.EnrollmentDto;
import com.learnhub.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.title", target = "courseName")
    EnrollmentDto toDto(Enrollment enrollment);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEntity(EnrollmentDto dto);

    List<EnrollmentDto> toDtoList(List<Enrollment> enrollments);

    List<Enrollment> toEntityList(List<EnrollmentDto> dtos);

}