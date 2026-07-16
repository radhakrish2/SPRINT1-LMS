package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.AnnouncementDto;
import com.learnhub.dto.ApiResponse;
import com.learnhub.service.AnnouncementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    // Create Announcement
    @PostMapping
    public ResponseEntity<ApiResponse<AnnouncementDto>> createAnnouncement(
             @RequestBody AnnouncementDto announcementDto) {

        AnnouncementDto announcement =
                announcementService.createAnnouncement(announcementDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Announcement created successfully.",
                        announcement));
    }

    // Update Announcement
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AnnouncementDto>> updateAnnouncement(
            @PathVariable Long id,
            @Valid @RequestBody AnnouncementDto announcementDto) {

        AnnouncementDto announcement =
                announcementService.updateAnnouncement(id, announcementDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Announcement updated successfully.",
                        announcement));
    }

    // Get Announcement by Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnnouncementDto>> getAnnouncementById(
            @PathVariable Long id) {

        AnnouncementDto announcement =
                announcementService.getAnnouncementById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Announcement retrieved successfully.",
                        announcement));
    }

    // Get All Announcements
    @GetMapping
    public ResponseEntity<ApiResponse<List<AnnouncementDto>>> getAllAnnouncements() {

        List<AnnouncementDto> announcements =
                announcementService.getAllAnnouncements();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Announcements retrieved successfully.",
                        announcements));
    }

    // Get Active Announcements
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<AnnouncementDto>>> getActiveAnnouncements() {

        List<AnnouncementDto> announcements =
                announcementService.getActiveAnnouncements();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Active announcements retrieved successfully.",
                        announcements));
    }

    // Get Course Announcements
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<AnnouncementDto>>> getAnnouncementsByCourse(
            @PathVariable Long courseId) {

        List<AnnouncementDto> announcements =
                announcementService.getAnnouncementsByCourse(courseId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course announcements retrieved successfully.",
                        announcements));
    }

    // Delete Announcement
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAnnouncement(
            @PathVariable Long id) {

        announcementService.deleteAnnouncement(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Announcement deleted successfully.",
                        null));
    }

}