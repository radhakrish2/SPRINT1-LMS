package com.learnhub.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CertificateDto {

    private Long id;

    private String certificateNumber;

    private LocalDate issuedDate;

    private String studentName;

    private String courseName;

}