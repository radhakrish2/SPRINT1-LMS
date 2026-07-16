package com.learnhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseIdOrderByLessonOrder(Long courseId);
    List<Lesson> findByCourseId(Long courseId);
}