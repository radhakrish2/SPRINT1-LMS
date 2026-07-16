
package com.learnhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Progress;



public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByStudentId(Long studentId);

    Optional<Progress> findByStudentIdAndCourseId(Long studentId, Long courseId);
    

}