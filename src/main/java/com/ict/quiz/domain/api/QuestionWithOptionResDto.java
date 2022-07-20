package com.ict.quiz.domain.api;

import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class QuestionWithOptionResDto {

    @Valid
    private Question question;
    @Valid
    private List<QuestionOption> options;
}
