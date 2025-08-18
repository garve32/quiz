// QuestionExplanationDto.java (API 응답용)
package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionExplanationDto {
    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "9")
    private Long question_id;

    @ApiModelProperty(example = "문제 해설")
    private String title;

    @ApiModelProperty(example = "이 문제는 다음과 같이 풀 수 있습니다...")
    private String explanation_text;

    private List<ExplanationImageDto> images;

    private LocalDateTime created_dt;
}