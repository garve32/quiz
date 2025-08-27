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

    // 해설 관련 메서드들
    List<QuestionExplanationPage> findAllExplanations(QuestionExplanationPage explanation);
    int findAllExplanationsCount(QuestionExplanationPage explanation);
    QuestionExplanationPage findExplanationById(Long id);
    QuestionExplanationPage findExplanationByQuestionId(Long questionId);
    void insertExplanation(QuestionExplanationPage explanation);
    void updateExplanation(QuestionExplanationPage explanation);
    void updateExplanationNotFile(QuestionExplanationPage explanation);
    List<QuestionPage> findQuestionsForExplanation();
    
    // 해설 등록 여부와 함께 문제 목록 조회
    List<QuestionPage> findQuestionsWithExplanationStatus(QuestionPage question);
    int findQuestionsWithExplanationStatusCount(QuestionPage question);
}
