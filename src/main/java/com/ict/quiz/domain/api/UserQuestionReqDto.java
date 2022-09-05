package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UserQuestionReqDto {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "172")
    private Long question_id;
    @ApiModelProperty(example = "3")
    private Long category_id;
    @ApiModelProperty(example = "1,7,9,11,13,15")
    private String question_set;
    @ApiModelProperty(example = "2,2,2,1,0,0")
    private String progress_set;
    @ApiModelProperty(example = "1,2:3,1,0,0,0")
    private String answer_set;
    @ApiModelProperty(example = "0,0,0,0,0,0")
    private String correct_set;
    @ApiModelProperty(example = "S")
    private String success_cd;
    @ApiModelProperty(example = "38")
    private int accum_sec;

}
