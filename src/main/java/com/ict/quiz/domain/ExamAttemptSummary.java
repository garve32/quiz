package com.ict.quiz.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamAttemptSummary {
    private Long user_id;
    private String user_login_id;
    private String user_name;
    private Integer seq;
    private Long category_id;
    private String category_name;
    private Double score_percent;
    private String success_cd;
    private LocalDateTime start_dt;
    private LocalDateTime end_dt;
}


