package com.learnhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByUserId(Long userId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}