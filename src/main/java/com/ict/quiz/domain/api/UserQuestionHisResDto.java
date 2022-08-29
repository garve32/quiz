package com.ict.quiz.domain.api;

import com.ict.quiz.domain.UserQuestion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserQuestionHisResDto {

    public UserQuestionHisResDto(Long id, Long user_id, int seq, Long category_id
    , String question_set, String progress_set, String answer_set, String correct_set
    , LocalDateTime start_dt, LocalDateTime end_dt
    , String category_nm, int question_cnt, int correct_cnt) {
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
        this.setCategory_nm(category_nm);
        this.setQuestion_cnt(question_cnt);
        this.setCorrect_cnt(correct_cnt);

    }
    @ApiModelProperty(position = 1, example = "172")
    private Long id;
    @ApiModelProperty(example = "1")
    private Long user_id;
    @ApiModelProperty(example = "107")
    private int seq;
    @ApiModelProperty(example = "3")
    private Long category_id;
    @ApiModelProperty(example = "1,7,9,11,13,15")
    private String question_set;
    @ApiModelProperty(example = "2,2,2,2,2,2")
    private String progress_set;
    @ApiModelProperty(example = "1,2:3,1,3,3,2:3:4")
    private String answer_set;
    @ApiModelProperty(example = "1,1,0,1,1,0")
    private String correct_set;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start_dt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end_dt;
    @ApiModelProperty(example = "S")
    private String success_cd;
    @ApiModelProperty(example = "AWS")
    private String category_nm;

    @ApiModelProperty(example = "6")
    private int question_cnt;
    @ApiModelProperty(example = "")
    private int correct_cnt;
}
