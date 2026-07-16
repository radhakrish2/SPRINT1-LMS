package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.StudentDto;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    StudentDto updateStudent(Long studentId, StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    StudentDto getStudentByEmail(String email);

    StudentDto getStudentByUserId(Long userId);

    List<StudentDto> getAllStudents();

    void deleteStudent(Long studentId);

}