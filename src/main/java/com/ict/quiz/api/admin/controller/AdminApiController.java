package com.ict.quiz.api.admin.controller;

import com.ict.quiz.api.admin.service.AdminApiService;
import com.ict.quiz.dto.Question;
import com.ict.quiz.dto.QuestionOption;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/adm/")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminApiService adminService;

    @PostMapping("/qst/")
    public ResponseEntity saveQuestion(@RequestPart Question question,
                                       @RequestPart List<QuestionOption> options,
                                       @RequestPart(value = "image") MultipartFile multipartFile) {
        // 문제 생성
        adminService.createQuestion(question, multipartFile);

        // 옵션 생성
        adminService.createQuestionOptions(options);

        // 저장

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
