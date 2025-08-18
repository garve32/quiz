// ExplanationImageDto.java
package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExplanationImageDto {
    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "https://example.com/images/explanation1.jpg")
    private String image_url;

    @ApiModelProperty(example = "explanation1.jpg")
    private String image_name;

    @ApiModelProperty(example = "문제 풀이 과정을 보여주는 이미지")
    private String image_description;

    @ApiModelProperty(example = "1")
    private Integer seq;
}