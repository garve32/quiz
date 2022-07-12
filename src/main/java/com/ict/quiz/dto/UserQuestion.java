package com.ict.quiz.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserQuestion {

    private Long id;
    private Long user_id;
    private int seq;
    private Long category_id;
    private String question_set;
    private String progress_set;
    private String correct_set;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_dt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end_dt;


}
