package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.StudentDto;
import com.learnhub.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "user.id", target = "userId")
    StudentDto toDto(Student student);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    Student toEntity(StudentDto dto);

    List<StudentDto> toDtoList(List<Student> students);

}