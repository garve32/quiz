package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    private Long id;

    private String name;
    private Long p_id;
    private int question_cnt;
    private int success_cnt;
}
