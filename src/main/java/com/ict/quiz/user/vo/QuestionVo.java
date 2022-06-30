package com.ict.quiz.user.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
//@RequiredArgsConstructor
@NoArgsConstructor
public class QuestionVo {

    private Long id;
    private String text;
    private byte[] image;
    private int seq;
    private String useYn;
    private Long categoryId;
}
