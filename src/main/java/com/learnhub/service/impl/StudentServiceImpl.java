package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.StudentDto;
import com.learnhub.entity.Student;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.StudentMapper;
import com.learnhub.repository.StudentRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto createStudent(StudentDto dto) {

        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(
                    "Student already exists with email : " + dto.getEmail());
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id : " + dto.getUserId()));

        Student student = studentMapper.toEntity(dto);

        student.setUser(user);

        student = studentRepository.save(student);

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto dto) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());

        student = studentRepository.save(student);

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto getStudentByEmail(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with email : " + email));

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto getStudentByUserId(Long userId) {

        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found for user id : " + userId));

        return studentMapper.toDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {

        return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Override
    public void deleteStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        studentRepository.delete(student);
    }

}