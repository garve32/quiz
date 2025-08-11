package com.ict.quiz.api.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.quiz.domain.User;
import com.ict.quiz.domain.api.HisDetailDto;
import com.ict.quiz.domain.api.UserQuestionHisResDto;

@Mapper
public interface UserApiMapper {
    List<UserQuestionHisResDto> findHisList(Long user_id);

    int checkDup(User user);

    void insertUser(User user);

    HisDetailDto findHisDetail(Long id);

    User findUser(String login_id);
}
