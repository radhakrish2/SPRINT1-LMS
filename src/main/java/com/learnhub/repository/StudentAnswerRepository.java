package com.learnhub.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.StudentAnswer;


public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    List<StudentAnswer> findByStudentId(Long studentId);

    List<StudentAnswer> findByQuestionId(Long questionId);

}