package com.ict.quiz.domain.api;

import lombok.*;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryStatsResDto {
    
    @ApiModelProperty(example = "3")
    private Integer select_count;
    
    @ApiModelProperty(example = "100")
    private Integer attempt_count;
    
    @ApiModelProperty(example = "85.5")
    private Double correct_rate;

}
