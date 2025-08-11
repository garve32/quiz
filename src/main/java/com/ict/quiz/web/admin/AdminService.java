package com.ict.quiz.web.admin;

import com.ict.quiz.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    public List<QuestionPage> findAllQuestions(QuestionPage question) {

        int cnt = adminMapper.findAllQuestionsCount(question);
        Pagination pagination = new Pagination(question);
        pagination.setTotalRecordCount(cnt);

        question.setPagination(pagination);

        List<QuestionPage> allQuestions = adminMapper.findAllQuestions(question);

        return allQuestions;
    }

    public void insertQuestion(Question q) {
        adminMapper.insertQuestion(q);
    }

    public void updateQuestion(Question q) {
        adminMapper.updateQuestion(q);
    }

    public void updateQuestionNotFile(Question q) {
        adminMapper.updateQuestionNotFile(q);
    }

    public void insertOption(QuestionOption o) {
        adminMapper.insertOption(o);
    }

    public void updateOption(QuestionOption o) {
        adminMapper.updateOption(o);
    }

    public List<Category> findAllCategories() {
        return adminMapper.findAllCategories();
    }

    public List<Category> findCategories() {
        return adminMapper.findCategories();
    }

    public Category getCategoryById(Long id) {
        return adminMapper.findCategoryById(id);
    }

    public List<QuestionStat> findQuestionSelectStatsByCategory(QuestionStat params) {
        int total = adminMapper.findQuestionSelectStatsCountByCategory(params.getCategory_id());
        Pagination pagination = new Pagination(params);
        pagination.setTotalRecordCount(total);
        params.setPagination(pagination);

        return adminMapper.findQuestionSelectStatsByCategory(
                params.getCategory_id(),
                params.getRecordsPerPage(),
                params.getPagination().getFirstRecordIndex()
        );
    }

    public CategorySummary findCategorySummary(Long categoryId) {
        return adminMapper.findCategorySummary(categoryId);
    }

    public void updateCategory(Category category) {
        adminMapper.updateCategory(category);
    }

    public void insertCategory(Category category) {
        adminMapper.insertCategory(category);
    }

    public List<ExamAttemptSummary> findRecentExamAttempts(ExamAttemptPage params) {
        int total = adminMapper.countExamAttempts();
        Pagination pagination = new Pagination(params);
        pagination.setTotalRecordCount(total);
        params.setPagination(pagination);

        return adminMapper.findRecentExamAttempts(
                params.getRecordsPerPage(),
                params.getPagination().getFirstRecordIndex()
        );
    }
}
