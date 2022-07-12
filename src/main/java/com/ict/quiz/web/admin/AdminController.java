package com.ict.quiz.web.admin;

import com.ict.quiz.api.admin.service.AdminApiService;
import com.ict.quiz.domain.Criteria;
import com.ict.quiz.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping()
    public String adminForm(Model model) throws Exception {

        return "admin/adminForm";
    }

    @GetMapping("/questions")
    public String questions(@ModelAttribute("params") Question question, Model model) throws Exception {
        List<Question> questionList = adminService.findAllQuestions(question);
        model.addAttribute("list", questionList);
        return "admin/questions";
    }

    @PostMapping
    public String saveQuestion() {
        return null;
    }
}
