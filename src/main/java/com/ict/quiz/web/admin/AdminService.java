package com.ict.quiz.web.admin;

import com.ict.quiz.domain.Criteria;
import com.ict.quiz.domain.Pagination;
import com.ict.quiz.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    public List<Question> findAllQuestions(Question question) {

        int cnt = adminMapper.findAllQuestionsCount(question);
        Pagination pagination = new Pagination(question);
        pagination.setTotalRecordCount(cnt);

        question.setPagination(pagination);

        List<Question> allQuestions = adminMapper.findAllQuestions(question);


        return allQuestions;
    }
}
