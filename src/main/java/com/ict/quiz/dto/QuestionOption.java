package com.ict.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionOption {

    private Long id;
    private int number;
    private int seq;
    private String text;
    private String correct_yn;
    private Long question_id;
}
