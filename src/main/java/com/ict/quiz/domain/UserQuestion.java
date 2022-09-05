package com.ict.quiz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestion {

    @ApiModelProperty(position = 1, example = "172")
    private Long id;
    @ApiModelProperty(example = "1")
    private Long user_id;
    @ApiModelProperty(example = "107")
    private int seq;
    @ApiModelProperty(example = "3")
    private Long category_id;
    @ApiModelProperty(example = "1,7,9,11,13,15")
    private String question_set;
    @ApiModelProperty(example = "2,2,2,2,2,2")
    private String progress_set;
    @ApiModelProperty(example = "1,2:3,1,3,3,2:3:4")
    private String answer_set;
    @ApiModelProperty(example = "1,1,0,1,1,0")
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
