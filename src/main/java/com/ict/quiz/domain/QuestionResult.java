package com.ict.quiz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class QuestionResult {

    @ApiModelProperty(example = "9")
    private Long id;
    @ApiModelProperty(example = "Returning consumers may log in...")
    private String text;
    @ApiModelProperty(example = "9.png")
    private String image_name;
    @ApiModelProperty(example = "iVBORw0KGgoAAAANSUhEUgAAAPIAAA...")
    private byte[] image;
    private String encoded_image;
    @ApiModelProperty(example = "0")
    private int seq;
    @ApiModelProperty(example = "Y")
    private String use_yn;
    @ApiModelProperty(example = "S")
    private String type;
    @Positive(message = "카테고리는 필수입니다.")
    @ApiModelProperty(example = "3")
    private Long category_id;

    private String correct_yn;
}
