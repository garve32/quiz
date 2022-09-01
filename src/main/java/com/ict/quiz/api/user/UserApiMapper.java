package com.ict.quiz.api.user;

import com.ict.quiz.domain.HisDetail;
import com.ict.quiz.domain.User;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.UserQuestionHisResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserApiMapper {
    List<UserQuestionHisResDto> findHisList(Long user_id);

    int checkDup(User user);

    void insertUser(User user);

    HisDetail findHisDetail(Long id);

    User findUser(String login_id);
}
