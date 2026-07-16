package com.learnhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByInstructorId(Long instructorId);

    List<Course> findByCategoryName(String category);
    
    List<Course> findByCategoryId(Long categoryId);

    List<Course> findByTitleContainingIgnoreCase(String keyword);

}