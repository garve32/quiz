package com.ict.quiz.web.login;

import com.ict.quiz.SessionConst;
import com.ict.quiz.domain.User;
import com.ict.quiz.session.SessionManager;
import com.ict.quiz.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final SessionManager sessionManager;

    private final LoginService loginService;

    @GetMapping("/")
    public String home(@Login User loginUser, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        int uqCnt = loginService.countUserQuestionByUserId(loginUser.getId());
        model.addAttribute("uqCnt", uqCnt);
        return "loginHome";
    }

    @GetMapping("/users/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/users/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        User loginUser = loginService.login(form.getLogin_id(), form.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:" + redirectURL;

    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
