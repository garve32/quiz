package com.ict.quiz.api.user.controller;

import com.ict.quiz.api.user.service.QuestionApiService;
import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/q")
public class QuestionApiController {

    private final QuestionApiService questionService;

    @GetMapping("/category")
    public ResponseEntity getCategory() {

        List<CategoryResDto> categoryDto = questionService.findAllCategoryInfo();

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestion(@PathVariable("id") Long id) {
        QuestionResDto question = questionService.findById(id);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity test(@RequestBody Map<String, Object> req) {

        // 문제 랜덤 pick
        UserQuestion userQuestion = questionService.pickRandomQuestion(req);

        // 사용자화 문제 저장
        //questionService.saveUserQuestion(userQuestion);

        String[] q_set = userQuestion.getQuestion_set().split(",");

        // 정상 리턴
        return new ResponseEntity(q_set, HttpStatus.OK);
    }
}
