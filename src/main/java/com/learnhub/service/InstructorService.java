package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.InstructorDto;

public interface InstructorService {

    InstructorDto createInstructor(InstructorDto instructorDto);

    InstructorDto updateInstructor(Long id, InstructorDto instructorDto);

    InstructorDto getInstructorById(Long id);

    List<InstructorDto> getAllInstructors();

    InstructorDto getInstructorByUserId(Long userId);

    void deleteInstructor(Long id);

}