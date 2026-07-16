package com.learnhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {

    private Long id;

    // User Details
    private Long userId;
    private String name;
    private String email;
    private String phone;

    // Instructor Details
    private String designation;
    private String specialization;
    private Integer experience;
    private String bio;

}