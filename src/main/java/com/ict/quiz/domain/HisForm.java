package com.ict.quiz.domain;

import lombok.Data;

@Data
public class HisForm {

    private Long id;
    private String category_nm;
    private String success_cd_nm;
    private int success_per;
    private int total_q_cnt;
    private int correct_cnt;
    private int wrong_cnt;
    private int correct_per;
    private String start_dt;
    private String end_dt;

}
