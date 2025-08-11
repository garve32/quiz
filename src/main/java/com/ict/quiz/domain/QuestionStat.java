package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStat extends Criteria {
    private Long question_id;
    private String question_text;
    private Long select_count;
    private Long correct_count;
    private Double correct_rate; // percent (0-100), rounded in SQL
    private Long category_id;

    public Pagination pagination;

    @Override
    public String makeQueryString(int pageNo) {
        // Keep base query from Criteria and add categoryId to persist filter across pages
        String base = super.makeQueryString(pageNo);
        String categoryQuery = (category_id != null) ? (base.isEmpty() ? "?" : "&") + "categoryId=" + category_id : "";
        return base + categoryQuery;
    }
}


