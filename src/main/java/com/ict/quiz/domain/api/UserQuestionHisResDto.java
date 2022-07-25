package com.ict.quiz.domain.api;

import com.ict.quiz.domain.UserQuestion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserQuestionHisResDto extends UserQuestion {

    public UserQuestionHisResDto(Long id, Long user_id, int seq, Long category_id
    , String question_set, String progress_set, String answer_set, String correct_set
    , LocalDateTime start_dt, LocalDateTime end_dt
    , int question_cnt, int correct_cnt) {
        this.setId(id);
        this.setUser_id(user_id);
        this.setSeq(seq);
        this.setCategory_id(category_id);
        this.setQuestion_set(question_set);
        this.setProgress_set(progress_set);
        this.setAnswer_set(answer_set);
        this.setCorrect_set(correct_set);
        this.setStart_dt(start_dt);
        this.setEnd_dt(end_dt);
        this.setQuestion_cnt(question_cnt);
        this.setCorrect_cnt(correct_cnt);

    }

    @ApiModelProperty(example = "6")
    private int question_cnt;
    @ApiModelProperty(example = "")
    private int correct_cnt;
}
