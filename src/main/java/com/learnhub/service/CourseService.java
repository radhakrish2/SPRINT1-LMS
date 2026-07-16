package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.CourseDto;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(Long id, CourseDto courseDto);

    CourseDto getCourseById(Long id);

    List<CourseDto> getAllCourses();

    List<CourseDto> getCoursesByInstructor(Long instructorId);

    List<CourseDto> searchCourses(String keyword);

    void deleteCourse(Long id);
    
    List<CourseDto> getCoursesByCategory(Long categoryId);

}