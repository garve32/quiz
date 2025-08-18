// ExplanationImage.java
package com.ict.quiz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExplanationImage {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "1")
    private Long explanation_id;

    @ApiModelProperty(example = "https://example.com/images/explanation1.jpg")
    private String image_url;

    @ApiModelProperty(example = "explanation1.jpg")
    private String image_name;

    @ApiModelProperty(example = "문제 풀이 과정을 보여주는 이미지")
    private String image_description;

    @ApiModelProperty(example = "1")
    private Integer seq;

    @ApiModelProperty(example = "Y")
    private String use_yn;

    @ApiModelProperty(example = "N")
    private String del_yn;

    private LocalDateTime created_dt;
}