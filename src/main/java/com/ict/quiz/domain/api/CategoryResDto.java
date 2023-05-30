package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResDto {

        @ApiModelProperty(example = "3")
        private Long id;
        @ApiModelProperty(example = "1")
        private Long p_id;
        @ApiModelProperty( example = "DVA-C01")
        private String name;
        @ApiModelProperty( example = "questions order and response orders are randomized\\n- you can only review the answer after finishing the exam due to how Udemy works")
        private String description;
        @ApiModelProperty( example = "130")
        private int time_limit;
        @ApiModelProperty( example = "5")
        private int question_cnt;
        @ApiModelProperty( example = "P")
        private String success_type;
        @ApiModelProperty( example = "2")
        private int success_cnt;
        @ApiModelProperty( example = "72")
        private int success_percent;
        @ApiModelProperty( example = "Y")
        private String use_yn;
        @ApiModelProperty( example = "https://image.com/image.png")
        private String logo_url;
        @ApiModelProperty( example = "Y")
        private String active_yn;
        @ApiModelProperty( example = "120")
        private String pool_cnt;

}
