package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UserQuestionResDto {

    @ApiModelProperty(position = 1, example = "172")
    private Long id;
    @ApiModelProperty(position = 2, example = "1")
    private Long user_id;
    @ApiModelProperty(example = "107")
    private int seq;
    @ApiModelProperty(example = "3")
    private Long category_id;
    @ApiModelProperty(example = "1,7,9,11,13,15")
    private String question_set;
    @ApiModelProperty(example = "0,0,0,0,0,0")
    private String progress_set;
    @ApiModelProperty(example = "0,0,0,0,0,0")
    private String answer_set;
    @ApiModelProperty(example = "0,0,0,0,0,0")
    private String correct_set;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start_dt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end_dt;
    @ApiModelProperty(example = "S")
    private String success_cd;
    @ApiModelProperty(example = "38")
    private int accum_sec;
}
