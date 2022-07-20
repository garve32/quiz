package com.ict.quiz.domain.api;

import lombok.Getter;

@Getter
public class QuestionResDto {

    private Long id;
    private String text;
    private String image_name;
    private byte[] image;
    private int seq;
    private String use_yn;
    private String type;
    private Long category_id;
}
