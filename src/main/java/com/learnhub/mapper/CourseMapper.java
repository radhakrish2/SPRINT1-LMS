package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.CourseDto;
import com.learnhub.entity.Course;


@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "instructor.id", target = "instructorId")
    @Mapping(source = "instructor.user.name", target = "instructorName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "category")
    CourseDto toDto(Course course);

    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "certificates", ignore = true)
    @Mapping(target = "progressList", ignore = true)
    Course toEntity(CourseDto dto);

    List<CourseDto> toDtoList(List<Course> courses);
}