package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class QuestionStartReqDto {

    @ApiModelProperty(example = "1")
    private Long user_id;
    @ApiModelProperty(example = "3")
    private Long category_id;
    @ApiModelProperty(example = "62")
    private int question_cnt;
}
