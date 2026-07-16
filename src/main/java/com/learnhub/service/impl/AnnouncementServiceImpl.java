package com.learnhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnhub.dto.AnnouncementDto;
import com.learnhub.entity.Announcement;
import com.learnhub.entity.Course;
import com.learnhub.entity.User;
import com.learnhub.exception.ResourceNotFoundException;
import com.learnhub.mapper.AnnouncementMapper;
import com.learnhub.repository.AnnouncementRepository;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.UserRepository;
import com.learnhub.service.AnnouncementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final AnnouncementMapper announcementMapper;

    // Create Announcement
    @Override
    public AnnouncementDto createAnnouncement(AnnouncementDto dto) {

        Announcement announcement = announcementMapper.toEntity(dto);

        // Course (Optional)
        if (dto.getCourseId() != null) {

            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Course not found with id : " + dto.getCourseId()));

            announcement.setCourse(course);
        }

        // Created By
        User user = userRepository.findById(dto.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id : " + dto.getCreatedBy()));

        announcement.setCreatedBy(user);

        announcement.setCreatedAt(LocalDateTime.now());

        if (announcement.getExpiryDate() == null) {
            announcement.setExpiryDate(LocalDateTime.now().plusDays(30));
        }

        announcement.setActive(true);

        Announcement saved = announcementRepository.save(announcement);

        return announcementMapper.toDto(saved);
    }

    // Update Announcement
    @Override
    public AnnouncementDto updateAnnouncement(Long id, AnnouncementDto dto) {

        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Announcement not found with id : " + id));

        announcement.setTitle(dto.getTitle());
        announcement.setMessage(dto.getMessage());
        announcement.setExpiryDate(dto.getExpiryDate());
        announcement.setActive(dto.isActive());

        // Update Course
        if (dto.getCourseId() != null) {

            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Course not found with id : " + dto.getCourseId()));

            announcement.setCourse(course);

        } else {

            announcement.setCourse(null);
        }

        Announcement updated = announcementRepository.save(announcement);

        return announcementMapper.toDto(updated);
    }

    // Get By Id
    @Override
    @Transactional(readOnly = true)
    public AnnouncementDto getAnnouncementById(Long id) {

        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Announcement not found with id : " + id));

        return announcementMapper.toDto(announcement);
    }

    // Get All
    @Override
    @Transactional(readOnly = true)
    public List<AnnouncementDto> getAllAnnouncements() {

        return announcementRepository.findAll()
                .stream()
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get Active
    @Override
    @Transactional(readOnly = true)
    public List<AnnouncementDto> getActiveAnnouncements() {

        return announcementRepository.findByActiveTrue()
                .stream()
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get By Course
    @Override
    @Transactional(readOnly = true)
    public List<AnnouncementDto> getAnnouncementsByCourse(Long courseId) {

        if (!courseRepository.existsById(courseId)) {

            throw new ResourceNotFoundException(
                    "Course not found with id : " + courseId);
        }

        return announcementRepository.findByCourse_Id(courseId)
                .stream()
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }

    // Delete
    @Override
    public void deleteAnnouncement(Long id) {

        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Announcement not found with id : " + id));

        announcementRepository.delete(announcement);
    }

}