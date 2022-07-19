package com.ict.quiz.domain;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class QuestionWithOptionReqDto {

    @Valid
    private Question question;
    @Valid
    private List<QuestionOption> options;
}
