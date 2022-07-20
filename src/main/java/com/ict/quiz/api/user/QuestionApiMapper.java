package com.ict.quiz.api.user;

import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionResDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionApiMapper {

    Question findById(Long id);

    List<CategoryResDto> findAllCategoryInfo();

    UserQuestion pickRandomQuestion(Map<String, Object> req);

    void saveUserQuestion(UserQuestion userQuestion);


    void updateUserQuestion(UserQuestionReqDto req);

    void endUserQuestion(UserQuestionReqDto req);

    List<QuestionOption> findOptionByQuestionId(Long question_id);
}
