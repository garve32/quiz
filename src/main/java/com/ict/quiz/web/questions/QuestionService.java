package com.ict.quiz.web.questions;

import com.ict.quiz.domain.Category;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;

    public UserQuestion pickRandomQuestion(Object req) {
        return questionMapper.pickRandomQuestion(req);
    }

    public Question findById(Long id) {
        return questionMapper.findById(id);
    }

    public List<QuestionOption> findByQuestionId(Long question_id) {
        return questionMapper.findByQuestionId(question_id);
    }

}
