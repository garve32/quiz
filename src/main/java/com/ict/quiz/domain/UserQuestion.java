package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestion {

    private Long id;
    private Long user_id;
    private int seq;
    private Long category_id;
    private String question_set;
    private String progress_set;
    private String answer_set;
    private String correct_set;
    private LocalDateTime start_dt;
    private LocalDateTime end_dt;
}
