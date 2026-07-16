package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.CertificateDto;

public interface CertificateService {

	CertificateDto generateCertificate(Long studentId, Long courseId);

	CertificateDto getCertificateById(Long id);

	List<CertificateDto> getCertificatesByStudent(Long studentId);

	List<CertificateDto> getCertificatesByCourse(Long courseId);

	void deleteCertificate(Long id);

}