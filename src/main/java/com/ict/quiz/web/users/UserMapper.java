package com.ict.quiz.web.users;

import com.ict.quiz.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findById(Long id);

    Optional<User> findByLoginId(String login_id);

    void save(User member);

    int countUserQuestionByUserId(Long user_id);
}
