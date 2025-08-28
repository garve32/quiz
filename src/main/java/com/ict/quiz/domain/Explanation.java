package com.ict.quiz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Explanation {

    @ApiModelProperty(example = "9")
    private Long id;
    @ApiModelProperty(example = "11")
    private Long question_id;
    @ApiModelProperty(example = "Returning consumers may log in...")
    private String explanation_text;
    @ApiModelProperty(example = "9.png")
    private String image_name;
    @ApiModelProperty(example = "iVBORw0KGgoAAAANSUhEUgAAAPIAAA...")
    private byte[] image;
    private String encoded_image;
    @ApiModelProperty(example = "Y")
    private String use_yn;
}
