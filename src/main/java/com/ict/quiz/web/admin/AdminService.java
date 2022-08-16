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

}
