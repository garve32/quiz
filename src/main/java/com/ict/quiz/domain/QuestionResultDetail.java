package com.ict.quiz.domain;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class QuestionResultDetail {

    @Valid
    private QuestionResult question;
    @Valid
    private List<QuestionOptionResult> options;
}
