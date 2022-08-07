package com.ict.quiz.web.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.quiz.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
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

    public ResultForm saveScore(UserQuestion userQuestion) {
        String[] q_set = userQuestion.getQuestion_set().split(",");
        String[] a_set = userQuestion.getAnswer_set().split(",");
        String[] c_set = new String[q_set.length];
        int correct_cnt = 0;

        List<QuestionResultDetail> questionResultDetails = new LinkedList<>();

        for (int i = 0; i < q_set.length; i++) {
            String q = q_set[i];
            String correctAnswer = questionMapper.findCorrectByQuestionId(Long.valueOf(q));
            if(correctAnswer.equals(a_set[i])) {
                c_set[i] = "1";
                correct_cnt++;
            } else {
                c_set[i] = "0";
            }
            QuestionResultDetail questionResultDetail = new QuestionResultDetail();
            ObjectMapper om = new ObjectMapper();
            Question question = questionMapper.findById(Long.valueOf(q));

            QuestionResult questionResult = om.convertValue(question, QuestionResult.class);
            questionResult.setCorrect_yn("1".equals(c_set[i]) ? "Y" : "N");
            if(question.getImage() != null &&question.getImage().length > 0) {
                String image = Base64.encodeBase64String(question.getImage());
                questionResult.setEncoded_image(image);
            }
            questionResultDetail.setQuestion(questionResult);

            List<QuestionOption> options = questionMapper.findByQuestionId(Long.valueOf(q));
            List<QuestionOptionResult> questionOptionResults = new LinkedList<>();
            for (QuestionOption option : options) {
                QuestionOptionResult questionOptionResult = om.convertValue(option, QuestionOptionResult.class);
                String[] split = a_set[i].split(":");
                boolean b = Arrays.stream(split).anyMatch(a -> String.valueOf(option.getId()).equals(a));
                //log.info("question = {}, a_set[i] = {}, option_id = {}, boolean = {}", q, a_set[i], option.getId(), b);
                if(b) {
                    questionOptionResult.setSelect_yn("Y");
                } else {
                    questionOptionResult.setSelect_yn("N");
                }
                questionOptionResults.add(questionOptionResult);
            }
            questionResultDetail.setOptions(questionOptionResults);
            questionResultDetails.add(questionResultDetail);

        }

        // 카테고리별 성공 기준 측정
        Category category = questionMapper.getCategory(userQuestion.getCategory_id());
        String success_type = category.getSuccess_type();
        String success_cd = "F";
        String success_cd_nm = "불합격";
        int correct_percent = 0;

        if("C".equals(success_type)) {
            int success_cnt = category.getSuccess_cnt();
            if(correct_cnt >= success_cnt) {
                success_cd = "S";
                success_cd_nm = "합격";
            }
        } else {
            int success_percent = category.getSuccess_percent();
            correct_percent = (int) Math.round( ((double)correct_cnt / (double)q_set.length * 100));
            if(correct_percent >= success_percent) {
                success_cd = "S";
                success_cd_nm = "합격";
            }
        }

        String correct_set = String.join(",", c_set);
        userQuestion.setCorrect_set(correct_set);
        userQuestion.setSuccess_cd(success_cd);
        questionMapper.updateUserQuestion(userQuestion);

        // 풀이한 문제 셋의 결과
        ResultForm resultForm = new ResultForm();
        resultForm.setId(userQuestion.getId());
        resultForm.setCategory_nm(category.getName());
        resultForm.setSuccess_cd_nm(success_cd_nm);
        resultForm.setSuccess_per(category.getSuccess_percent());
        resultForm.setTotal_q_cnt(q_set.length);
        resultForm.setCorrect_cnt(correct_cnt);
        resultForm.setWrong_cnt(q_set.length - correct_cnt);
        resultForm.setCorrect_per(correct_percent);
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a HH:mm");
        String start_dt = userQuestion.getStart_dt().format(fm);
        String end_dt = userQuestion.getEnd_dt().format(fm);
        resultForm.setStart_dt(start_dt);
        resultForm.setEnd_dt(end_dt);
        // 풀이한 문제 리스트
        resultForm.setResultDetails(questionResultDetails);

        return resultForm;
    }
}
