package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    private Long id;
    private Long p_id;
    private String name;
    private String description;
    private int time_limit;
    private int question_cnt;
    private String logo_url;
    private String success_type;
    private int success_cnt;
    private int success_percent;
    private String use_yn;
    private String active_yn;
    private int seq;
}
