package com.learnhub.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnhub.dto.LessonProgressDto;
import com.learnhub.entity.Lesson;
import com.learnhub.entity.LessonProgress;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.LessonProgressMapper;
import com.learnhub.repository.LessonProgressRepository;
import com.learnhub.repository.LessonRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.LessonProgressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonProgressServiceImpl implements LessonProgressService {

    private final LessonProgressRepository lessonProgressRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final LessonProgressMapper lessonProgressMapper;

    @Override
    public LessonProgressDto createLessonProgress(Long studentId, Long lessonId) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id : " + studentId));

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found with id : " + lessonId));

        LessonProgress progress = new LessonProgress();

        progress.setUser(student);
        progress.setLesson(lesson);
        progress.setVideoCompleted(false);
        progress.setQuizPassed(false);

        LessonProgress saved = lessonProgressRepository.save(progress);

        return lessonProgressMapper.toDto(saved);
    }

    @Override
    public LessonProgressDto markVideoCompleted(Long progressId) {

        LessonProgress progress = lessonProgressRepository.findById(progressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson Progress not found with id : " + progressId));

        progress.setVideoCompleted(true);

        LessonProgress updated = lessonProgressRepository.save(progress);

        return lessonProgressMapper.toDto(updated);
    }

    @Override
    public LessonProgressDto markQuizPassed(Long progressId) {

        LessonProgress progress = lessonProgressRepository.findById(progressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson Progress not found with id : " + progressId));

        progress.setQuizPassed(true);

        LessonProgress updated = lessonProgressRepository.save(progress);

        return lessonProgressMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public LessonProgressDto getLessonProgress(Long progressId) {

        LessonProgress progress = lessonProgressRepository.findById(progressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson Progress not found with id : " + progressId));

        return lessonProgressMapper.toDto(progress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonProgressDto> getStudentProgress(Long studentId) {

        if (!userRepository.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "Student not found with id : " + studentId);
        }

        return lessonProgressRepository.findByUserId(studentId)
                .stream()
                .map(lessonProgressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonProgressDto> getLessonProgressByLesson(Long lessonId) {

        if (!lessonRepository.existsById(lessonId)) {
            throw new ResourceNotFoundException(
                    "Lesson not found with id : " + lessonId);
        }

        return lessonProgressRepository.findByLessonId(lessonId)
                .stream()
                .map(lessonProgressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLessonProgress(Long progressId) {

        LessonProgress progress = lessonProgressRepository.findById(progressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson Progress not found with id : " + progressId));

        lessonProgressRepository.delete(progress);
    }

}