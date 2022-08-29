package com.ict.quiz.api.user;

import com.ict.quiz.domain.*;
import com.ict.quiz.domain.api.CategoryResDto;
import com.ict.quiz.domain.api.QuestionStartReqDto;
import com.ict.quiz.domain.api.UserQuestionReqDto;
import com.ict.quiz.domain.api.UserQuestionResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void endUserQuestion(UserQuestionReqDto userQuestion) {

        String[] q_set = userQuestion.getQuestion_set().split(",");
        String[] a_set = userQuestion.getAnswer_set().split(",");
        String[] c_set = new String[q_set.length];
        int correct_cnt = 0;

        for (int i = 0; i < q_set.length; i++) {
            String q = q_set[i];
            //log.info("q = {}", q);
            //log.info("a = {}", a_set[i]);
            String correctAnswer = questionMapper.findCorrectByQuestionId(Long.valueOf(q));
            if(correctAnswer.equals(a_set[i])) {
                //log.info("##{} 번 문제 맞음!", q);
                c_set[i] = "1";
                correct_cnt++;
            } else {
                //log.info("##{} 번 문제 틀림!", q);
                c_set[i] = "0";

            }
        }

        // 카테고리별 성공 기준 측정
        Category category = questionMapper.getCategory(userQuestion.getCategory_id());
        String success_type = category.getSuccess_type();
        String success_cd = "F";

        if("C".equals(success_type)) {
            int success_cnt = category.getSuccess_cnt();
            if(correct_cnt >= success_cnt) {
                success_cd = "S";
            }
        } else {
            int success_percent = category.getSuccess_percent();
            if(correct_cnt /q_set.length >= success_percent / 100) {
                success_cd = "S";
            }
        }

        String correct_set = String.join(",", c_set);
        //log.info("correct_set = {}", correct_set);
        userQuestion.setCorrect_set(correct_set);
        userQuestion.setSuccess_cd(success_cd);


        questionMapper.endUserQuestion(userQuestion);
    }

    public List<QuestionOption> findOptionByQuestionId(Long question_id) {
        return questionMapper.findOptionByQuestionId(question_id);
    }

    public UserQuestionResDto findUserQuestionById(Long id) {
        return questionMapper.findUserQuestionById(id);
    }
}
