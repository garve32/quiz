package com.ict.quiz.domain.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    private Long id;

    @NotEmpty
    private String login_id; //로그인 ID
    @NotEmpty
    private String password;
    @NotEmpty
    private String name; //사용자 이름
    private String admin_yn;

}
