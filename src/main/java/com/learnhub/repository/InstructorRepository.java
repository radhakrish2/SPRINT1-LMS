package com.learnhub.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByUserId(Long userId);

}