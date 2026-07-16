package com.learnhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	List<Certificate> findByStudentId(Long studentId);

	List<Certificate> findByCourseId(Long courseId);

	Optional<Certificate> findByStudentIdAndCourseId(Long studentId, Long courseId);

	boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);

}