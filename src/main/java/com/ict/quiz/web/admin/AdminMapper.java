package com.ict.quiz.web.admin;

import com.ict.quiz.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<QuestionStat> findQuestionSelectStatsByCategory(@Param("categoryId") Long categoryId, @Param("limit") int limit, @Param("offset") int offset);

    int findQuestionSelectStatsCountByCategory(@Param("categoryId") Long categoryId);

    CategorySummary findCategorySummary(@Param("categoryId") Long categoryId);

    void updateCategory(Category category);

    void insertCategory(Category category);

    List<ExamAttemptSummary> findRecentExamAttempts(@Param("limit") int limit, @Param("offset") int offset);
    int countExamAttempts();
}
