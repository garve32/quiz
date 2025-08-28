package com.ict.quiz.api.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.quiz.domain.Category;
import com.ict.quiz.domain.Explanation;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.CategoryResDto;
import com.ict.quiz.domain.api.QuestionStartReqDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
import com.ict.quiz.domain.api.UserQuestionResDto;

@Mapper
public interface QuestionApiMapper {

    Question findById(Long id);

    List<Question> findByIds(@Param("ids") List<Long> ids);

    List<CategoryResDto> findAllCategoryInfo();

    UserQuestion pickRandomQuestion(QuestionStartReqDto req);

    void saveUserQuestion(UserQuestion userQuestion);


    void updateUserQuestion(UserQuestionReqDto req);

    void endUserQuestion(UserQuestionReqDto req);

    List<QuestionOption> findOptionByQuestionId(Long question_id);

    List<QuestionOption> findOptionsByQuestionIds(@Param("questionIds") List<Long> questionIds);

    Category getCategory(Long id);

    String findCorrectByQuestionId(Long question_id);

    List<Explanation> findExplanationsByQuestionIds(@Param("questionIds") List<Long> questionIds);

    UserQuestionResDto findUserQuestionById(Long id);
}
