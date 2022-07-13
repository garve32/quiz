package com.ict.quiz.api.user.service;

import com.ict.quiz.api.user.mapper.QuestionApiMapper;
import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionApiService {

    private final QuestionApiMapper questionMapper;
    public QuestionResDto findById(Long id) {
        return questionMapper.findById(id);
    }

    public UserQuestion pickRandomQuestion(Map<String, Object> req) {
        return questionMapper.pickRandomQuestion(req);
    }

    public void saveUserQuestion(UserQuestion req) {
        questionMapper.saveUserQuestion(req);
    }

    public List<CategoryResDto> findAllCategoryInfo() {
        return questionMapper.findAllCategoryInfo();
    }
}
