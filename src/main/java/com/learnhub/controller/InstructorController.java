package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.InstructorDto;
import com.learnhub.service.InstructorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
@CrossOrigin("*")
public class InstructorController {

    private final InstructorService instructorService;

    // Create Instructor Profile
    @PostMapping
    public ResponseEntity<ApiResponse<InstructorDto>> createInstructor(
            @Valid @RequestBody InstructorDto instructorDto) {

        InstructorDto instructor =
                instructorService.createInstructor(instructorDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Instructor created successfully.",
                        instructor));
    }

    // Update Instructor
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InstructorDto>> updateInstructor(
            @PathVariable Long id,
            @Valid @RequestBody InstructorDto instructorDto) {

        InstructorDto instructor =
                instructorService.updateInstructor(id, instructorDto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor updated successfully.",
                        instructor));
    }

    // Get Instructor By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InstructorDto>> getInstructorById(
            @PathVariable Long id) {

        InstructorDto instructor =
                instructorService.getInstructorById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor retrieved successfully.",
                        instructor));
    }

    // Get Instructor By User Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<InstructorDto>> getInstructorByUserId(
            @PathVariable Long userId) {

        InstructorDto instructor =
                instructorService.getInstructorByUserId(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor retrieved successfully.",
                        instructor));
    }

    // Get All Instructors
    @GetMapping
    public ResponseEntity<ApiResponse<List<InstructorDto>>> getAllInstructors() {

        List<InstructorDto> instructors =
                instructorService.getAllInstructors();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructors retrieved successfully.",
                        instructors));
    }

    // Delete Instructor
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(
            @PathVariable Long id) {

        instructorService.deleteInstructor(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor deleted successfully.",
                        null));
    }

}