package com.ict.quiz.domain.api;

import com.ict.quiz.domain.QuestionResultDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserQuestionHisDetailResDto {

    @ApiModelProperty(position = 1, example = "172")
    private Long id;
    @ApiModelProperty(example = "1")
    private Long category_id;
    @ApiModelProperty(example = "AWS")
    private String category_nm;
    @ApiModelProperty(example = "S")
    private String success_cd;
    @ApiModelProperty(example = "합격")
    private String success_cd_nm;
    @ApiModelProperty(example = "72")
    private int success_per;
    @ApiModelProperty(example = "65")
    private int total_q_cnt;
    @ApiModelProperty(example = "58")
    private int correct_cnt;
    @ApiModelProperty(example = "7")
    private int wrong_cnt;
    @ApiModelProperty(example = "85")
    private int correct_per;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start_dt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end_dt;
    @ApiModelProperty(example = "38")
    private int accum_sec;
    @ApiModelProperty(example = "170")
    private int time_limit;

    private List<QuestionResultDetail> resultDetails;
}
