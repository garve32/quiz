// QuestionExplanation.java
package com.ict.quiz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionExplanation {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "9")
    private Long question_id;

    @ApiModelProperty(example = "문제 해설")
    private String title;

    @ApiModelProperty(example = "이 문제는 다음과 같이 풀 수 있습니다...")
    private String explanation_text;

    @ApiModelProperty(example = "Y")
    private String use_yn;

    private LocalDateTime created_dt;
    private LocalDateTime updated_dt;
}