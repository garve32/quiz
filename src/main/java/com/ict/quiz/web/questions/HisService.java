package com.ict.quiz.web.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.quiz.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HisService {

    private final HisMapper hisMapper;
    private final QuestionMapper questionMapper;
    public List<HisForm> getHisList(Long user_id) {
        return hisMapper.getHisList(user_id);
    }

    public ResultForm getHisDetail(Long id) {
        // 메인 조회
        HisDetail his = hisMapper.getHisDetail(id);

        List<QuestionResultDetail> questionResultDetails = new LinkedList<>();

        String[] q_set = his.getQuestion_set().split(",");
        String[] a_set = his.getAnswer_set().split(",");
        String[] c_set = his.getCorrect_set().split(",");

        for (int i = 0; i < q_set.length; i++) {
            String q = q_set[i];
            // 문제 조회
            ObjectMapper om = new ObjectMapper();
            QuestionResultDetail questionResultDetail = new QuestionResultDetail();
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

        ResultForm resultForm = new ResultForm();
        resultForm.setId(his.getId());
        resultForm.setCategory_nm(his.getCategory_nm());
        resultForm.setSuccess_cd_nm(his.getSuccess_cd_nm());
        resultForm.setSuccess_per(his.getSuccess_per());
        resultForm.setTotal_q_cnt(his.getTotal_q_cnt());
        resultForm.setCorrect_cnt(his.getCorrect_cnt());
        resultForm.setWrong_cnt(his.getWrong_cnt());
        resultForm.setCorrect_per(his.getCorrect_per());
        resultForm.setStart_dt(his.getStart_dt());
        resultForm.setEnd_dt(his.getEnd_dt());
        resultForm.setResultDetails(questionResultDetails);

        return resultForm;
    }
}
