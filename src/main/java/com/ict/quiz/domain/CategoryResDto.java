package com.ict.quiz.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResDto {

        private Long id;
        private String name;
        private Long p_id;
        private int question_cnt;
        private int success_cnt;

}
