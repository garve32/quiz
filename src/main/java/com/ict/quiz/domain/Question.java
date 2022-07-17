package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class Question {

    private Long id;
    private String text;
    private byte[] image;
    @Min(0)
    private int seq;
    private String use_yn;
    @NotBlank(message = "타입 설정해라")
    @Length(min = 1)
    private String type;
    @NotBlank(message = "카테고리 설정해라")
    private Long category_id;

}
