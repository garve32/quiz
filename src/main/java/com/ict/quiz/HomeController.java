package com.ict.quiz;

import com.ict.quiz.domain.user.User;
import com.ict.quiz.web.argumentresolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

//    @RequestMapping("/")
//    public String home() {
//        log.info("home controller");
//        return "home";
//    }

    @GetMapping("/")
    public String home(@Login User loginUser, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "loginHome";
    }
}
