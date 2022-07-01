package com.ict.quiz.user.mapper;

import com.ict.quiz.dto.Category;
import com.ict.quiz.dto.Question;
import com.ict.quiz.dto.UserQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface QuestionMapper {

    Question getQuestionById(Long id);

    UserQuestion pickRandomQuestion(Map<String, Object> req);

    Category getCategory(Long id);

    void saveUserQuestion(UserQuestion req);
}
