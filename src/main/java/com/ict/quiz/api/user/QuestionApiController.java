package com.ict.quiz.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionStartReqDto;
import com.ict.quiz.domain.api.QuestionWithOptionResDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
import com.ict.quiz.domain.api.UserQuestionResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/q")
public class QuestionApiController {

    private final QuestionApiService questionService;

    @ApiOperation(
            value = "카테고리 조회"
            , notes = "선택할 수 있는 카테고리정보를 조회한다."
            , response = CategoryResDto.class
            , responseContainer = "List"
    )
    @GetMapping("/category")
    public ResponseEntity getCategory() {

        List<CategoryResDto> categoryDto = questionService.findAllCategoryInfo();

        return ResponseEntity.ok(categoryDto);
    }

    @ApiOperation(
            value = "문제 단건 조회"
            , notes = "문제ID에 해당하는 문제를 선택지와 함께 조회한다."
            , response = QuestionWithOptionResDto.class
    )
    @GetMapping("/{id}")
    public ResponseEntity getQuestion(@PathVariable("id") Long id) {

        QuestionWithOptionResDto resDto = new QuestionWithOptionResDto();

        Question q = questionService.findById(id);
        List<QuestionOption> o = questionService.findOptionByQuestionId(id);

        resDto.setQuestion(q);
        resDto.setOptions(o);

        return ResponseEntity.ok(resDto);
    }


    @ApiOperation(
            value = "문제 시작"
            , notes = "카테고리를 선택하면 랜덤으로 문제가 출제되고, 사용자화 DB에 저장된다"
            , response = UserQuestionResDto.class
    )
    @PostMapping("/start")
    public ResponseEntity start(@RequestBody QuestionStartReqDto startReqDto) {

        // 문제 랜덤 pick
        UserQuestion userQuestion = questionService.pickRandomQuestion(startReqDto);

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
        return ResponseEntity.ok(userQuestionResDto);
    }

    @ApiOperation(
            value = "문제 이동"
            , notes = "화면에서 넘겨준 UserQuestion 데이터를 업데이트하고 \n" +
            "question_id 조건으로 다음 문제를 리턴한다."
            , response = QuestionWithOptionResDto.class
    )
    @PostMapping("/move")
    public ResponseEntity move(@RequestBody UserQuestionReqDto req) {

        //log.info("req = {}", req);
        questionService.updateUserQuestion(req);

        QuestionWithOptionResDto resDto = new QuestionWithOptionResDto();

        Question q = questionService.findById(req.getQuestion_id());
        //log.info("q = {}", q);
        List<QuestionOption> o = questionService.findOptionByQuestionId(req.getQuestion_id());
        //log.info("o = {}", o);

        resDto.setQuestion(q);
        resDto.setOptions(o);

        //log.info("resDto = {}", resDto);

        return ResponseEntity.ok(resDto);
    }

    @ApiOperation(
            value = "문제 최종 제출"
            , notes = "화면에서 넘겨준 최종 UserQuestion 데이터를 업데이트하고 \n" +
            "채점 후 UserQuestion 데이터를 리턴한다. \n" +
            "리턴값은 화면에서 보여줄 데이터에 따라 변경될 수 있다."
            , response = UserQuestionResDto.class
    )
    @PostMapping("/end")
    public ResponseEntity end(@RequestBody UserQuestionReqDto req) {

        questionService.endUserQuestion(req);

        UserQuestionResDto res = questionService.findUserQuestionById(req.getId());
        return ResponseEntity.ok(res);
    }
}
