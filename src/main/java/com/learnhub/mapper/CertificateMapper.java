package com.learnhub.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.CertificateDto;
import com.learnhub.entity.Certificate;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "course.title", target = "courseName")
    CertificateDto toDto(Certificate certificate);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Certificate toEntity(CertificateDto dto);

    List<CertificateDto> toDtoList(List<Certificate> certificates);

    List<Certificate> toEntityList(List<CertificateDto> dtos);

}