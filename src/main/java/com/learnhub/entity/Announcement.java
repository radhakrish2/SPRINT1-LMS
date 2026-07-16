package com.learnhub.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "announcements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000)
    private String message;

    private LocalDateTime createdAt;

    private LocalDateTime expiryDate;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;     // null = Global Announcement

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

}