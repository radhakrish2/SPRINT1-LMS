package com.learnhub.service.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnhub.dto.CertificateDto;
import com.learnhub.entity.Certificate;
import com.learnhub.entity.Course;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.CertificateMapper;
import com.learnhub.repository.CertificateRepository;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.CertificateService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class CertificateServiceImpl implements CertificateService {


    private final CertificateRepository certificateRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final CertificateMapper certificateMapper;



    // Generate Certificate
    @Override
    public CertificateDto generateCertificate(
            Long studentId,
            Long courseId) {


        User student =
                userRepository.findById(studentId)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : "
                                + studentId));



        Course course =
                courseRepository.findById(courseId)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : "
                                + courseId));



        // Avoid duplicate certificate
        if(certificateRepository
                .existsByStudent_IdAndCourse_Id(
                        studentId,
                        courseId)) {


            throw new RuntimeException(
                    "Certificate already generated");
        }



        Certificate certificate = new Certificate();


        certificate.setStudent(student);


        certificate.setCourse(course);


        certificate.setIssuedDate(
                LocalDate.now()
        );


        certificate.setCertificateNumber(
                "CERT-" 
                + System.currentTimeMillis()
        );



        Certificate savedCertificate =
                certificateRepository.save(
                        certificate);



        return certificateMapper.toDto(
                savedCertificate);
    }





    // Get Certificate By Id
    @Override
    @Transactional(readOnly = true)
    public CertificateDto getCertificateById(
            Long id) {


        Certificate certificate =
                certificateRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Certificate not found with id : "
                                + id));



        return certificateMapper.toDto(
                certificate);
    }





    // Get Certificates By Student
    @Override
    @Transactional(readOnly = true)
    public List<CertificateDto> getCertificatesByStudent(
            Long studentId) {


        if(!userRepository.existsById(studentId)) {


            throw new ResourceNotFoundException(
                    "Student not found with id : "
                    + studentId);
        }



        return certificateRepository
                .findByStudentId(studentId)

                .stream()

                .map(certificateMapper::toDto)

                .collect(Collectors.toList());
    }





    // Get Certificates By Course
    @Override
    @Transactional(readOnly = true)
    public List<CertificateDto> getCertificatesByCourse(
            Long courseId) {


        if(!courseRepository.existsById(courseId)) {


            throw new ResourceNotFoundException(
                    "Course not found with id : "
                    + courseId);
        }



        return certificateRepository
                .findByCourseId(courseId)

                .stream()

                .map(certificateMapper::toDto)

                .collect(Collectors.toList());

    }





    // Delete Certificate
    @Override
    public void deleteCertificate(Long id) {


        Certificate certificate =
                certificateRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Certificate not found with id : "
                                + id));



        certificateRepository.delete(
                certificate);
    }

}