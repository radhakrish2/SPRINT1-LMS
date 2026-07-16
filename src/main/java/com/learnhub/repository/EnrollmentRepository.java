package com.learnhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnhub.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);

    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    
    @Query("""
    	       SELECT e.course.title, COUNT(e)
    	       FROM Enrollment e
    	       GROUP BY e.course.title
    	       """)
    	List<Object[]> getCourseEnrollmentStatistics();
    	
    	List<Enrollment> findTop10ByOrderByEnrollmentDateDesc();
}