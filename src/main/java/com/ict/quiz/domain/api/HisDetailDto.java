package com.ict.quiz.domain.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HisDetailDto {

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
    private LocalDateTime start_dt;
    private LocalDateTime end_dt;
    private int accum_sec;
    private int time_limit;

}
