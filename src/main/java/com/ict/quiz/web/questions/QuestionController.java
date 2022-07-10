package com.ict.quiz.web.questions;

import com.ict.quiz.domain.Category;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(3L, "DVA-C01", 1L, 3, 2));
        categoryList.add(new Category(4L, "Data분석", 2L, 3, 1));

        model.addAttribute("categoryList", categoryList);

        CategoryForm form = new CategoryForm();

        model.addAttribute("form", form);
        //model.addAttribute("q", null);
        return "questions/category";
    }

    @PostMapping("/start")
    public String start(CategoryForm form, Model model) throws Exception {

        log.info("CategoryForm = {}", form);
        log.info("model = {}", model);

        UserQuestion userQuestion = questionService.pickRandomQuestion(form);

        log.info("userQuestion = {}", userQuestion);
        model.addAttribute("uq", userQuestion);

        // 사용자화 문제 저장
//        questionService.saveUserQuestion(userQuestionVo);

        String first = userQuestion.getQuestion_set().split(",")[0];
        Long question_id = Long.valueOf(first);

        // 첫번째 문제 조회
        Question question = questionService.findById(question_id);
        log.info("question = {}", question);
        model.addAttribute("q", question);

        // 문제 옵션 조회
        List<QuestionOption> questionOptions = questionService.findByQuestionId(question_id);
        log.info("questionOptions = {}", questionOptions);
        model.addAttribute("options", questionOptions);

        return "questions/question";
    }

    @PostMapping("/question")
    public String question(Question q, Model model) throws Exception {

        log.info("model = {}", model);
        log.info("question = {}", q);

        Long question_id = q.getId();

        // 문제 조회
        Question question = questionService.findById(question_id);
        log.info("question = {}", question);
        model.addAttribute("q", question);

        // 문제 옵션 조회
        List<QuestionOption> questionOptions = questionService.findByQuestionId(question_id);
        log.info("questionOptions = {}", questionOptions);
        model.addAttribute("options", questionOptions);

        UserQuestion userQuestion = new UserQuestion();

        model.addAttribute("uq", userQuestion);

        return "questions/question :: #q_item";
    }
}
