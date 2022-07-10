package com.ict.quiz.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String login_id;

    @NotEmpty
    private String password;
}
