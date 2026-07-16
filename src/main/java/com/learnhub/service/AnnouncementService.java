package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.AnnouncementDto;

public interface AnnouncementService {

    AnnouncementDto createAnnouncement(AnnouncementDto announcementDto);

    AnnouncementDto updateAnnouncement(Long id, AnnouncementDto announcementDto);

    AnnouncementDto getAnnouncementById(Long id);

    List<AnnouncementDto> getAllAnnouncements();

    List<AnnouncementDto> getActiveAnnouncements();

    List<AnnouncementDto> getAnnouncementsByCourse(Long courseId);

    void deleteAnnouncement(Long id);

}