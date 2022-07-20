package com.ict.quiz.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionResDto;
import com.ict.quiz.domain.api.QuestionWithOptionResDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
import com.ict.quiz.domain.api.UserQuestionResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        QuestionWithOptionResDto resDto = new QuestionWithOptionResDto();

        Question q = questionService.findById(id);
        List<QuestionOption> o = questionService.findOptionByQuestionId(id);

        resDto.setQuestion(q);
        resDto.setOptions(o);

        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity start(@RequestBody Map<String, Object> req) {

        // 문제 랜덤 pick
        UserQuestion userQuestion = questionService.pickRandomQuestion(req);

        // 사용자화 문제 저장
        questionService.saveUserQuestion(userQuestion);

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        UserQuestionResDto userQuestionResDto = om.convertValue(userQuestion, UserQuestionResDto.class);

//        String[] q_set_str = userQuestion.getQuestion_set().split(",");
//        Long[] q_set = new Long[q_set_str.length];
//
//        for (int i = 0; i < q_set.length; i++) {
//            q_set[i] = Long.valueOf(q_set_str[i]);
//        }

        // 정상 리턴
        return new ResponseEntity(userQuestionResDto, HttpStatus.OK);
    }

    @PostMapping("/move")
    public ResponseEntity move(@RequestBody UserQuestionReqDto req) {

        log.info("req = {}", req);
        questionService.updateUserQuestion(req);

        QuestionWithOptionResDto resDto = new QuestionWithOptionResDto();

        Question q = questionService.findById(req.getQuestion_id());
        log.info("q = {}", q);
        List<QuestionOption> o = questionService.findOptionByQuestionId(req.getQuestion_id());
        log.info("o = {}", o);

        resDto.setQuestion(q);
        resDto.setOptions(o);

        log.info("resDto = {}", resDto);

        return new ResponseEntity(resDto, HttpStatus.OK);
    }
}
