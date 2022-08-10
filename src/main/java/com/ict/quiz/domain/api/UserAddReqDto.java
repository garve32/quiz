package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAddReqDto {

    @ApiModelProperty(example = "id12")
    private String login_id;
    @ApiModelProperty(example = "qwer1234")
    private String password;
    @ApiModelProperty(example = "닉네임")
    private String name;

}
