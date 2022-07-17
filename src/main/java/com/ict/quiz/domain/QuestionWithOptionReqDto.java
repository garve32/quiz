package com.ict.quiz.domain;

import lombok.Data;

import java.util.List;

@Data
public class QuestionWithOptionReqDto {
//    private Long id;
//    private String text;
//    private byte[] image;
//    private int seq;
//    private String use_yn;
//    private String type;
//    private Long category_id;
    private Question question;

    private List<QuestionOption> options;
}
