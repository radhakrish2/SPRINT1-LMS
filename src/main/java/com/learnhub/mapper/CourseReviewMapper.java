package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.CourseReviewDto;
import com.learnhub.entity.CourseReview;

@Mapper(componentModel = "spring")
public interface CourseReviewMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.title", target = "courseName")
    CourseReviewDto toDto(CourseReview review);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    CourseReview toEntity(CourseReviewDto dto);

    List<CourseReviewDto> toDtoList(List<CourseReview> reviews);

}