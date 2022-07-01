package com.ict.quiz.user.service;

import com.ict.quiz.user.mapper.QuestionMapper;
import com.ict.quiz.dto.Category;
import com.ict.quiz.dto.Question;
import com.ict.quiz.dto.UserQuestion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;
    public Question getQuestionById(Long id) {
        return questionMapper.getQuestionById(id);
    }

    public UserQuestion pickRandomQuestion(Map<String, Object> req) {
        return questionMapper.pickRandomQuestion(req);
    }

    public void saveUserQuestion(UserQuestion req) {
        questionMapper.saveUserQuestion(req);
    }

    public Category getCategory(Long id) {
        return questionMapper.getCategory(id);
    }
}
