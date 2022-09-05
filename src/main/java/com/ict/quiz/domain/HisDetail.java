package com.ict.quiz.domain;

import lombok.Data;

@Data
public class HisDetail {

    private Long id;

    private String question_set;
    private String answer_set;
    private String correct_set;
    private Long category_id;
    private String category_nm;
    private String success_cd;
    private String success_cd_nm;
    private int success_per;
    private int total_q_cnt;
    private int correct_cnt;
    private int wrong_cnt;
    private int correct_per;
    private String start_dt;
    private String end_dt;
    private int accum_sec;

}
