package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Long id;
    private String text;
    private byte[] image;
    private int seq;
    private String use_yn;
    private Long category_id;

}
