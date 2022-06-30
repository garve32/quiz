package com.ict.quiz.user.service;

import com.ict.quiz.user.mapper.QuestionMapper;
import com.ict.quiz.user.vo.QuestionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;
    public QuestionVo getQuestionById() {
        return questionMapper.getQuestionById();
    }
}
