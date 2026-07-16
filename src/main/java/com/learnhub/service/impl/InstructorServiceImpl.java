package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.InstructorDto;
import com.learnhub.entity.Instructor;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.InstructorMapper;
import com.learnhub.repository.InstructorRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.InstructorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

	private final InstructorRepository instructorRepository;
	private final UserRepository userRepository;
	private final InstructorMapper instructorMapper;

	@Override
	public InstructorDto createInstructor(InstructorDto dto) {

		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Instructor instructor = instructorMapper.toEntity(dto);

		instructor.setUser(user);

		instructor = instructorRepository.save(instructor);

		return instructorMapper.toDto(instructor);
	}

	@Override
	public InstructorDto updateInstructor(Long id, InstructorDto dto) {

		Instructor instructor = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

		instructor.setDesignation(dto.getDesignation());
		instructor.setSpecialization(dto.getSpecialization());
		instructor.setExperience(dto.getExperience());
		instructor.setBio(dto.getBio());

		instructor = instructorRepository.save(instructor);

		return instructorMapper.toDto(instructor);
	}

	@Override
	public InstructorDto getInstructorById(Long id) {

		Instructor instructor = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

		return instructorMapper.toDto(instructor);
	}

	@Override
	public List<InstructorDto> getAllInstructors() {

		return instructorMapper.toDtoList(instructorRepository.findAll());
	}

	@Override
	public InstructorDto getInstructorByUserId(Long userId) {

		Instructor instructor = instructorRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

		return instructorMapper.toDto(instructor);
	}

	@Override
	public void deleteInstructor(Long id) {

		Instructor instructor = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

		instructorRepository.delete(instructor);
	}

}