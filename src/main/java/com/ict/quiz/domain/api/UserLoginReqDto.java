package com.ict.quiz.domain.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginReqDto {

    @ApiModelProperty(example = "id12")
    private String login_id;
    @ApiModelProperty(example = "qwer1234")
    private String password;

}
