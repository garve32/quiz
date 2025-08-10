package com.ict.quiz.web;

import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.web.questions.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final QuestionService questionService;

    @GetMapping("/preview/{id}")
    public String preview(@PathVariable("id") Long id, Model model) {

        // 문제 랜덤 pick
        HashMap<Object, Object> req = new HashMap<>();
        req.put("user_id", 1);
        req.put("category_id", 1);
        req.put("question_cnt", 1);
        UserQuestion userQuestion = questionService.pickRandomQuestion(req);
        userQuestion.setId(0L);
        model.addAttribute("uq", userQuestion);

        model.addAttribute("progress", "test");
        model.addAttribute("percent", "100");




        // 문제 조회
        Question question = questionService.findById(id);
        model.addAttribute("q", question);
        if(question.getImage() != null &&question.getImage().length > 0) {
            String image = Base64.encodeBase64String(question.getImage());
            model.addAttribute("image", image);
        }

        // 문제 옵션 조회
        List<QuestionOption> questionOptions = questionService.findByQuestionId(id);
        model.addAttribute("options", questionOptions);

        return "questions/question";
    }
}
