package com.ict.quiz.domain;

import lombok.*;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOption {

    private Long id;
    private int number;
    @Positive
    private int seq;
    private String text;
    private String correct_yn;
    private Long question_id;

    private String del_yn;
}
