package com.ict.quiz.web.questions;

import com.ict.quiz.domain.HisForm;
import com.ict.quiz.domain.User;
import com.ict.quiz.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/his")
@RequiredArgsConstructor
public class HisController {

    private final HisService hisService;

    @GetMapping("/list")
    public String list(@Login User loginUser, Model model) throws Exception {
        Long user_id = loginUser.getId();
        List<HisForm> historyList = hisService.getHisList(user_id);
        model.addAttribute("hl", historyList);

        return "questions/history";
    }

    @GetMapping("/detail/{id}")
    public String detail(@Login User loginUser, @PathVariable("id") Long id, Model model) throws Exception {
        ResultForm rf = hisService.getHisDetail(id);
        model.addAttribute("r", rf);

        return "questions/historyDetail";
    }

}
