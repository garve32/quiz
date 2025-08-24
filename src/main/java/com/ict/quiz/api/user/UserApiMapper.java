package com.ict.quiz.api.user;

import java.util.List;

import com.ict.quiz.domain.api.CategoryResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.quiz.domain.User;
import com.ict.quiz.domain.api.CategoryStatsResDto;
import com.ict.quiz.domain.api.HisDetailDto;
import com.ict.quiz.domain.api.UserQuestionHisResDto;

@Mapper
public interface UserApiMapper {
    List<UserQuestionHisResDto> findHisList(Long user_id);

    int checkDup(User user);

    void insertUser(User user);

    HisDetailDto findHisDetail(Long id);

    User findUser(String login_id);

    List<CategoryResDto> findCategoryHis(Long user_id);

    List<CategoryStatsResDto> findCategoryStats(Long user_id, Long category_id);
}
