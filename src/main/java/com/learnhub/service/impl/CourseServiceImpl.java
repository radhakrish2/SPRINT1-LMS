package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.CourseDto;
import com.learnhub.entity.Category;
import com.learnhub.entity.Course;
import com.learnhub.entity.Instructor;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.CourseMapper;
import com.learnhub.repository.CategoryRepository;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.InstructorRepository;
import com.learnhub.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CategoryRepository categoryRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto createCourse(CourseDto dto) {

        Instructor instructor = instructorRepository.findById(dto.getInstructorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Instructor not found with id : " + dto.getInstructorId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + dto.getCategoryId()));

        Course course = courseMapper.toEntity(dto);

        course.setInstructor(instructor);
        course.setCategory(category);

        course = courseRepository.save(course);

        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : " + id));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setThumbnail(dto.getThumbnail());
        course.setDuration(dto.getDuration());
        course.setLevel(dto.getLevel());

        if (dto.getInstructorId() != null) {

            Instructor instructor = instructorRepository.findById(dto.getInstructorId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Instructor not found"));

            course.setInstructor(instructor);
        }

        if (dto.getCategoryId() != null) {

            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Category not found"));

            course.setCategory(category);
        }

        course = courseRepository.save(course);

        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : " + id));

        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {

        return courseMapper.toDtoList(courseRepository.findAll());
    }

    @Override
    public List<CourseDto> getCoursesByInstructor(Long instructorId) {

        return courseMapper.toDtoList(
                courseRepository.findByInstructorId(instructorId));
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {

        return courseMapper.toDtoList(
                courseRepository.findByTitleContainingIgnoreCase(keyword));
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : " + id));

        courseRepository.delete(course);
    }
    
    @Override
    public List<CourseDto> getCoursesByCategory(Long categoryId) {


        categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : "
                                + categoryId));


        List<Course> courses =
                courseRepository.findByCategoryId(categoryId);


        return courseMapper.toDtoList(courses);
    }

}