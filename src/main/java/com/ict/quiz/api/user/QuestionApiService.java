package com.ict.quiz.api.user;

import com.ict.quiz.domain.CategoryResDto;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.QuestionStartReqDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
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
    public Question findById(Long id) {
        return questionMapper.findById(id);
    }

    public UserQuestion pickRandomQuestion(QuestionStartReqDto req) {
        return questionMapper.pickRandomQuestion(req);
    }

    public void saveUserQuestion(UserQuestion req) {
        questionMapper.saveUserQuestion(req);
    }

    public List<CategoryResDto> findAllCategoryInfo() {
        return questionMapper.findAllCategoryInfo();
    }

    public void updateUserQuestion(UserQuestionReqDto req) {
        questionMapper.updateUserQuestion(req);
    }

    public void endUserQuestion(UserQuestionReqDto req) {
        questionMapper.endUserQuestion(req);
    }

    public List<QuestionOption> findOptionByQuestionId(Long question_id) {
        return questionMapper.findOptionByQuestionId(question_id);
    }
}
