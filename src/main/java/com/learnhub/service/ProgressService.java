package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.ProgressDto;

public interface ProgressService {

	ProgressDto createProgress(Long studentId, Long courseId);

	ProgressDto updateProgress(Long progressId, ProgressDto progressDto);

	ProgressDto getProgress(Long studentId, Long courseId);

	List<ProgressDto> getStudentProgress(Long studentId);

	void deleteProgress(Long progressId);

}