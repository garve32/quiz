package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryStatsCategoryResDto {

        @ApiModelProperty(example = "3")
        private Long id;
        @ApiModelProperty( example = "DVA-C01")
        private String name;
        @ApiModelProperty( example = "https://image.com/image.png")
        private String logo_url;
        @ApiModelProperty( example = "10")
        private int attempt_count;

}
