// 기존 HisDetailDto 확장 (해설 정보 포함)
package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HisDetailWithExplanationDto extends HisDetailDto {
    @ApiModelProperty(example = "문제별 해설 정보")
    private List<QuestionExplanationDto> explanations;
}