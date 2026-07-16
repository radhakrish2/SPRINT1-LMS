package com.learnhub.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;

    @OneToMany(mappedBy = "student")
	private List<QuizResult> quizResults;

	@OneToMany(mappedBy = "student")
	private List<StudentAnswer> studentAnswers;

	@OneToMany(mappedBy = "student")
	private List<Certificate> certificates;

	@OneToMany(mappedBy = "student")
	private List<Progress> progressList;

	@OneToMany(mappedBy = "student")
	private List<CourseReview> reviews;

}