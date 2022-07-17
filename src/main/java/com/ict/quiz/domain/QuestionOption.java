package com.ict.quiz.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOption {

    private Long id;
    private int number;
    private int seq;
    private String text;
    private String correct_yn;
    private Long question_id;

    private String del_yn;
}
