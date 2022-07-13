package com.ict.quiz.api.user.mapper;

import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionApiMapper {

    QuestionResDto findById(Long id);

    List<CategoryResDto> findAllCategoryInfo();

    UserQuestion pickRandomQuestion(Map<String, Object> req);

    void saveUserQuestion(UserQuestion userQuestion);


}
