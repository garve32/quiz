package com.ict.quiz.web.questions;


import com.ict.quiz.domain.Category;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {

    Category getCategory(Long id);

    UserQuestion pickRandomQuestion(Object req);

    Question findById(Long id);

    List<QuestionOption> findByQuestionId(Long question_id);
}
