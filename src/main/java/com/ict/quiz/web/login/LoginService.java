package com.ict.quiz.web.login;

import com.ict.quiz.domain.user.User;
import com.ict.quiz.web.users.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    //private final MemberRepository memberRepository;
    private final UserMapper userMapper;

    /**
     * @return null 로그인 실패
     */
    public User login(String login_id, String password) {
        return userMapper.findByLoginId(login_id)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
//        return memberRepository.findByLoginId(loginId)
//                .filter(m -> m.getPassword().equals(password))
//                .orElse(null);

    }
}
