package com.ict.quiz.web.questions;

import com.ict.quiz.domain.Category;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.UserQuestion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;

    public UserQuestion pickRandomQuestion(Object req) {
        return questionMapper.pickRandomQuestion(req);
    }

    public Question findById(Long id) {
        return questionMapper.findById(id);
    }

    public List<QuestionOption> findByQuestionId(Long question_id) {
        return questionMapper.findByQuestionId(question_id);
    }

    public List<Category> findLeaf() {
        return questionMapper.findLeaf();
    }

    public void saveUserQuestion(UserQuestion userQuestion) {
        questionMapper.saveUserQuestion(userQuestion);
    }

    public void updateUserQuestion(UserQuestion userQuestion) {
        questionMapper.updateUserQuestion(userQuestion);
    }

    public String saveScore(UserQuestion userQuestion) {
        String result = "";
        String[] q_set = userQuestion.getQuestion_set().split(",");
        String[] a_set = userQuestion.getAnswer_set().split(",");
        String[] c_set = new String[q_set.length];
        int correct_cnt = 0;

        for (int i = 0; i < q_set.length; i++) {
            String q = q_set[i];
            log.info("q = {}", q);
            log.info("a = {}", a_set[i]);
            String correctAnswer = questionMapper.findCorrectByQuestionId(Long.valueOf(q));
            if(correctAnswer.equals(a_set[i])) {
                log.info("##{} 번 문제 맞음!", q);
                result += q+"번 문제 맞음!";
                c_set[i] = "1";
                correct_cnt++;
            } else {
                log.info("##{} 번 문제 틀림!", q);
                result += q+"번 문제 틀림!";
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
        log.info("correct_set = {}", correct_set);
        userQuestion.setCorrect_set(correct_set);
        userQuestion.setSuccess_cd(success_cd);
        questionMapper.updateUserQuestion(userQuestion);
        return result;
    }
}
