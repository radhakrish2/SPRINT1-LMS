package com.learnhub.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByLessonId(Long lessonId);

}