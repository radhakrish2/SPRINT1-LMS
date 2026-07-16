package com.learnhub.dto;

import lombok.Data;

@Data
public class QuestionRequestDto {

    private String questionText;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctAnswer;

}