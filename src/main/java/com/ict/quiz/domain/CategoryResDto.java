package com.ict.quiz.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResDto {

        private Long id;
        private Long p_id;
        private String name;
        private String description;
        private int time_limit;
        private int question_cnt;
        private String success_type;
        private int success_cnt;
        private int success_percent;
        private String use_yn;

}
