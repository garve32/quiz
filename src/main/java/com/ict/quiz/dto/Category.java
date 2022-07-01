package com.ict.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Category {

    private Long id;
    private String name;
    private Long p_id;
    private int question_cnt;
    private int success_cnt;
}
