package com.ict.quiz.domain.api;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UserQuestionReqDto {

    private Long id;
    private Long question_id;
    private String progress_set;
    private String answer_set;

}
