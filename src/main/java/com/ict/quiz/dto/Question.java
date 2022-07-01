package com.ict.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Question {

    private Long id;
    private String text;
    private byte[] image;
    private int seq;
    private String use_yn;
    private Long category_id;
}
