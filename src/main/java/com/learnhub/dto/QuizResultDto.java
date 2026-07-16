package com.learnhub.dto;

import lombok.Data;

@Data
public class QuizResultDto {

	private Long id;

	private Integer score;

	private Integer totalQuestions;

	private Double percentage;

	private Long studentId;

	private String studentName;

	private Long quizId;

	private String quizTitle;

}