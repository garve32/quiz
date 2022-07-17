package com.ict.quiz.web.admin;

import com.ict.quiz.domain.Criteria;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.QuestionPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<QuestionPage> findAllQuestions(QuestionPage question);

    int findAllQuestionsCount(QuestionPage question);

    void addQuestion(Question question);

    void addOption(QuestionOption option);

    void updateQuestion(Question question);

    void updateOption(QuestionOption option);
}
