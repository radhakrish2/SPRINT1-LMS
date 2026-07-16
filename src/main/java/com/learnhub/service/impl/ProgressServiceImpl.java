package com.learnhub.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnhub.dto.ProgressDto;
import com.learnhub.entity.Course;
import com.learnhub.entity.Progress;
import com.learnhub.entity.ProgressStatus;
import com.learnhub.entity.Roles;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.ProgressMapper;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.ProgressRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.ProgressService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class ProgressServiceImpl implements ProgressService {


    private final ProgressRepository progressRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final ProgressMapper progressMapper;



    // Create Progress when student enrolls course
    @Override
    public ProgressDto createProgress(Long studentId,
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



        Progress progress = new Progress();


        progress.setStudent(student);

        progress.setCourse(course);

        progress.setPercentage(0.0);

        progress.setStatus(
                ProgressStatus.NOT_STARTED
        );



        Progress savedProgress =
                progressRepository.save(progress);



        return progressMapper.toDto(savedProgress);
    }





    // Update progress percentage/status
    @Override
    public ProgressDto updateProgress(
            Long progressId,
            ProgressDto progressDto) {


        Progress progress =
                progressRepository.findById(progressId)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Progress not found with id : "
                                + progressId));



        progress.setPercentage(
                progressDto.getPercentage()
        );


        if(progressDto.getStatus()!=null) {

            progress.setStatus(
                    progressDto.getStatus()
            );

        }


        // Automatically update status
        if(progress.getPercentage() >= 100) {

            progress.setStatus(
                    ProgressStatus.COMPLETED
            );

        }

        else if(progress.getPercentage() > 0) {

            progress.setStatus(
                    ProgressStatus.IN_PROGRESS
            );

        }



        Progress updatedProgress =
                progressRepository.save(progress);



        return progressMapper.toDto(updatedProgress);
    }





    // Get progress of particular student/course
    @Override
    @Transactional(readOnly = true)
    public ProgressDto getProgress(
            Long studentId,
            Long courseId) {


        Progress progress =
                progressRepository
                .findByStudentIdAndCourseId(
                        studentId,
                        courseId
                )

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Progress not found"));


        return progressMapper.toDto(progress);
    }





    // Get all courses progress of student
    @Override
    @Transactional(readOnly = true)
    public List<ProgressDto> getStudentProgress(
            Long studentId) {


        if(!userRepository.existsById(studentId)) {

            throw new ResourceNotFoundException(
                    "Student not found with id : "
                    + studentId);
        }



        return progressRepository
                .findByStudentId(studentId)

                .stream()

                .map(progressMapper::toDto)

                .collect(Collectors.toList());
    }





    // Delete Progress
    @Override
    public void deleteProgress(Long progressId) {


        Progress progress =
                progressRepository.findById(progressId)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Progress not found with id : "
                                + progressId));


        progressRepository.delete(progress);
    }

}