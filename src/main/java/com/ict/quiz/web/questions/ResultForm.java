package com.ict.quiz.web.questions;

import com.ict.quiz.domain.QuestionResultDetail;
import lombok.Data;

import java.util.List;

@Data
public class ResultForm {

    private Long id;
    private String category_nm;
    private String success_cd_nm;
    private int success_per;
    private int total_q_cnt;
    private int correct_cnt;
    private int wrong_cnt;
    private int correct_per;
    private String start_dt;
    private String end_dt;

    private List<QuestionResultDetail> resultDetails;

}
