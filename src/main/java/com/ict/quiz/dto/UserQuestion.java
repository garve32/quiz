package com.ict.quiz.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserQuestion {

    private Long id;
    private Long user_id;
    private int seq;
    private Long category_id;
    private String question_set;
    private String progress_set;
    private String correct_set;
    private LocalDateTime start_dt;
    private LocalDateTime end_dt;


}
