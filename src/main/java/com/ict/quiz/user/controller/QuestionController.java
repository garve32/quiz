package com.ict.quiz.user.controller;

import com.ict.quiz.user.service.QuestionService;
import com.ict.quiz.user.vo.QuestionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/user")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/qst/{id}")
    public QuestionVo getQuestion() {
        return questionService.getQuestionById();
    }
}
