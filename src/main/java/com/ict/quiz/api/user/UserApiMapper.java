package com.ict.quiz.api.user;

import com.ict.quiz.domain.User;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.UserQuestionHisResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserApiMapper {
    List<UserQuestion> findHisList(Long user_id);

    int checkDup(User user);

    void insertUser(User user);
}
