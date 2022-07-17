package com.ict.quiz.web.users;

import com.ict.quiz.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User save(User user) {
        log.info("save : user = {}", user);
        userMapper.save(user);
        return user;
    }
}
