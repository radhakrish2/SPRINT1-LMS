package com.learnhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnhub.entity.Announcement;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {

    List<Announcement> findByCourse_Id(Long courseId);

    List<Announcement> findByActiveTrue();

}