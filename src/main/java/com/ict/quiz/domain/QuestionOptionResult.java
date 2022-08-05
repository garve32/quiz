package com.ict.quiz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionResult {

    @ApiModelProperty(example = "3")
    private Long id;
    private int number;
    @ApiModelProperty(example = "1")
    @Positive
    private int seq;
    @ApiModelProperty(example = "선택지 1번")
    private String text;
    @ApiModelProperty(example = "Y or N")
    private String correct_yn;
    @ApiModelProperty(example = "172")
    private Long question_id;
    @ApiModelProperty(example = "N")
    private String del_yn;

    private String select_yn;
}
