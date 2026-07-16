package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.CertificateDto;
import com.learnhub.service.CertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CertificateController {

    private final CertificateService certificateService;

    // Generate Certificate
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<CertificateDto>> generateCertificate(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        CertificateDto certificate =
                certificateService.generateCertificate(studentId, courseId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Certificate generated successfully.",
                        certificate));
    }

    // Get Certificate By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CertificateDto>> getCertificateById(
            @PathVariable Long id) {

        CertificateDto certificate =
                certificateService.getCertificateById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Certificate retrieved successfully.",
                        certificate));
    }

    // Get Student Certificates
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<CertificateDto>>> getStudentCertificates(
            @PathVariable Long studentId) {

        List<CertificateDto> certificates =
                certificateService.getCertificatesByStudent(studentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Student certificates retrieved successfully.",
                        certificates));
    }

    // Get Course Certificates
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<CertificateDto>>> getCourseCertificates(
            @PathVariable Long courseId) {

        List<CertificateDto> certificates =
                certificateService.getCertificatesByCourse(courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course certificates retrieved successfully.",
                        certificates));
    }

    // Delete Certificate
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCertificate(
            @PathVariable Long id) {

        certificateService.deleteCertificate(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Certificate deleted successfully.",
                        null));
    }

}