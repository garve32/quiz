package com.ict.quiz.domain.api;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserQuestionHisResDto {

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
    @ApiModelProperty(example = "AWS")
    private String category_nm;

    @ApiModelProperty(example = "6")
    private int question_cnt;
    @ApiModelProperty(example = "3")
    private int correct_cnt;
    @ApiModelProperty(example = "http://logo.url")
    private String logo_url;
    @ApiModelProperty(example = "95")
    private int accum_sec;
    @ApiModelProperty(example = "170")
    private int time_limit;
}
