package com.ict.quiz.api.user;

import com.ict.quiz.domain.*;
import com.ict.quiz.domain.api.CategoryResDto;
import com.ict.quiz.domain.api.QuestionStartReqDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
import com.ict.quiz.domain.api.UserQuestionResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionApiMapper {

    Question findById(Long id);

    List<CategoryResDto> findAllCategoryInfo();

    UserQuestion pickRandomQuestion(QuestionStartReqDto req);

    void saveUserQuestion(UserQuestion userQuestion);


    void updateUserQuestion(UserQuestionReqDto req);

    void endUserQuestion(UserQuestionReqDto req);

    List<QuestionOption> findOptionByQuestionId(Long question_id);

    Category getCategory(Long id);

    String findCorrectByQuestionId(Long question_id);

    UserQuestionResDto findUserQuestionById(Long id);
}
