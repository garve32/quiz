package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class Question {

    private Long id;
    private String text;
    private String image_name;
    private byte[] image;
    private int seq;
    private String use_yn;
    private String type;
    @Positive(message = "카테고리는 필수입니다.")
    private Long category_id;
}
