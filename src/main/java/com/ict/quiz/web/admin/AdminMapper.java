package com.ict.quiz.web.admin;

import com.ict.quiz.domain.Criteria;
import com.ict.quiz.domain.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Question> findAllQuestions(Question question);

    int findAllQuestionsCount(Question question);
}
