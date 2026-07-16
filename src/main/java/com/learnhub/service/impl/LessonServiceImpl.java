package com.learnhub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnhub.dto.LessonDto;
import com.learnhub.entity.Course;
import com.learnhub.entity.Lesson;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.LessonMapper;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.LessonRepository;
import com.learnhub.service.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final LessonMapper lessonMapper;

   
    @Override
    public LessonDto createLesson(Long courseId,
                                  LessonDto lessonDto) {


        Course course = courseRepository.findById(courseId)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id : "
                                + courseId));


        Lesson lesson = new Lesson();


        lesson.setTitle(
                lessonDto.getTitle()
        );


        lesson.setDescription(
                lessonDto.getDescription()
        );


        lesson.setVideoUrl(
                lessonDto.getVideoUrl()
        );


        lesson.setPdfUrl(
                lessonDto.getPdfUrl()
        );


        lesson.setDuration(
                lessonDto.getDuration()
        );


        lesson.setLessonOrder(
                lessonDto.getLessonOrder()
        );


        lesson.setCourse(course);



        Lesson savedLesson =
                lessonRepository.save(lesson);


        return lessonMapper.toDto(savedLesson);
    }

    @Override
    public LessonDto updateLesson(Long id, LessonDto dto) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found with id : " + id));

        lesson.setTitle(dto.getTitle());
        lesson.setDescription(dto.getDescription());
        lesson.setVideoUrl(dto.getVideoUrl());
        lesson.setPdfUrl(dto.getPdfUrl());
        lesson.setDuration(dto.getDuration());
        lesson.setLessonOrder(dto.getLessonOrder());

        if (dto.getCourseId() != null) {

            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Course not found with id : " + dto.getCourseId()));

            lesson.setCourse(course);
        }

        lesson = lessonRepository.save(lesson);

        return lessonMapper.toDto(lesson);
    }

    @Override
    public LessonDto getLessonById(Long id) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found with id : " + id));

        return lessonMapper.toDto(lesson);
    }

  

    @Override
    public List<LessonDto> getLessonsByCourse(Long courseId) {

        return lessonMapper.toDtoList(
                lessonRepository.findByCourseId(courseId));
    }

    @Override
    public void deleteLesson(Long id) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found with id : " + id));

        lessonRepository.delete(lesson);
    }

}