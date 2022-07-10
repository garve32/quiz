package com.ict.quiz.web.questions;

import lombok.Data;

@Data
public class CategoryForm {

    private Long user_id;
    private Long category_id;
    private int question_cnt;
}
