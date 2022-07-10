package com.ict.quiz.web.user.controller;

import com.ict.quiz.web.user.service.QuestionApiService;
import com.ict.quiz.dto.Category;
import com.ict.quiz.dto.Question;
import com.ict.quiz.dto.UserQuestion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/user")
public class QuestionApiController {

    private final QuestionApiService questionService;

    @GetMapping("/qst/{id}")
    public ResponseEntity getQuestion(@PathVariable("id") Long id) {
        Question questionById = questionService.getQuestionById(id);
        return new ResponseEntity<>(questionById, HttpStatus.OK);
    }

    @PostMapping("/qst/test")
    public ResponseEntity test(@RequestBody Map<String, Object> req) {
        // 문제 랜덤 pick
        Long user_id = ((Number)req.get("user_id")).longValue();
        Long category_id = Long.valueOf(req.get("category_id").toString());

        // 문제 카테고리 정보 조회
        Category category = questionService.getCategory(category_id);
        log.info("category = {}", category);
        req.put("question_cnt", category.getQuestion_cnt());

        UserQuestion userQuestionVo = questionService.pickRandomQuestion(req);
        log.info("userQuestionVo = {}", userQuestionVo);

        // 사용자화 문제 저장
        questionService.saveUserQuestion(userQuestionVo);

        log.info("userQuestionVo = {}", userQuestionVo);

        // 정상 리턴
        return new ResponseEntity(HttpStatus.OK);
    }
}
