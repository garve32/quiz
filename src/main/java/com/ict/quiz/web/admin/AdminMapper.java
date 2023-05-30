package com.ict.quiz.web.admin;

import com.ict.quiz.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<QuestionPage> findAllQuestions(QuestionPage question);

    int findAllQuestionsCount(QuestionPage question);

    void insertQuestion(Question question);

    void updateQuestion(Question question);

    void updateQuestionNotFile(Question question);

    void insertOption(QuestionOption option);

    void updateOption(QuestionOption option);

    List<Category> findAllCategories();

    List<Category> findCategories();

    Category findCategoryById(Long id);
}
