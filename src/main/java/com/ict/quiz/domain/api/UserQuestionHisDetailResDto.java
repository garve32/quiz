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
    @ApiModelProperty(example = "2022년 08월 01일 13:00")
    private String start_dt;
    @ApiModelProperty(example = "2022년 08월 01일 13:00")
    private String end_dt;

    private List<QuestionResultDetail> resultDetails;
}
