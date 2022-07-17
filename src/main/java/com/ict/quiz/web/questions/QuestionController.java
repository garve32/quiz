package com.ict.quiz.web.questions;

import com.ict.quiz.domain.Category;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/category")
    public String category(Model model, HttpSession session) throws Exception {

        List<Category> leaf = questionService.findLeaf();
        model.addAttribute("categoryList", leaf);
        CategoryForm form = new CategoryForm();
        model.addAttribute("form", form);
        return "questions/category";
    }

    @PostMapping("/start")
    public String start(CategoryForm form, Model model) throws Exception {

        // 문제 랜덤 pick
        UserQuestion userQuestion = questionService.pickRandomQuestion(form);
        // 사용자화 문제 저장
        questionService.saveUserQuestion(userQuestion);
        model.addAttribute("uq", userQuestion);

        String[] q_set = userQuestion.getQuestion_set().split(",");

        String progress = "1/"+q_set.length;
        model.addAttribute("progress", progress);

        String first = q_set[0];
        Long question_id = Long.valueOf(first);

        // 첫번째 문제 조회
        Question question = questionService.findById(question_id);
        //log.info("question = {}", question);
        model.addAttribute("q", question);
        if(question.getImage() != null &&question.getImage().length > 0) {
            String image = Base64.encodeBase64String(question.getImage());
            model.addAttribute("image", image);
        }

        // 문제 옵션 조회
        List<QuestionOption> questionOptions = questionService.findByQuestionId(question_id);
        //log.info("questionOptions = {}", questionOptions);
        model.addAttribute("options", questionOptions);



        return "questions/question";
    }

    @PostMapping("/moveQuestion")
    public String question(UserQuestion userQuestion, Model model) throws Exception {

        log.info("model = {}", model);
        log.info("UserQuestion = {}", userQuestion);

        Long question_id = 0L;
        String progress = "/";
        String[] p_set = userQuestion.getProgress_set().split(",");
        String[] q_set = userQuestion.getQuestion_set().split(",");
        for (int i = 0; i < p_set.length; i++) {
            String s = p_set[i];
            if("1".equals(s)) {
                question_id = Long.valueOf(q_set[i]);
                progress = (i+1) + "/" + p_set.length;
                break;
            }
        }
        model.addAttribute("progress", progress);

        // 사용자화 문제 저장
        questionService.updateUserQuestion(userQuestion);
        log.info("userQuestion = {}", userQuestion);
        model.addAttribute("uq", userQuestion);

        // 문제 조회
        Question question = questionService.findById(question_id);
        //log.info("question = {}", question);
        model.addAttribute("q", question);
        if(question.getImage() != null &&question.getImage().length > 0) {
            String image = Base64.encodeBase64String(question.getImage());
            model.addAttribute("image", image);
        }

        // 문제 옵션 조회
        List<QuestionOption> questionOptions = questionService.findByQuestionId(question_id);
        //log.info("questionOptions = {}", questionOptions);
        model.addAttribute("options", questionOptions);


        return "questions/question :: #q_item";
    }

    @PostMapping("/end")
    public String end(@ModelAttribute UserQuestion userQuestion, Model model) {

        log.info("userQuestion = {}", userQuestion);
        log.info("model = {}", model);

        // 사용자화 문제 저장
        questionService.updateUserQuestion(userQuestion);

        // 채점
        String s = questionService.saveScore(userQuestion);
        log.info("s = {}", s);
        model.addAttribute("s", s);

        return "questions/result";
    }

}
