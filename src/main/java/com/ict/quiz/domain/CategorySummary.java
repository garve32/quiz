package com.ict.quiz.domain;

import lombok.Data;

@Data
public class CategorySummary {
    private Integer attempts;
    private Integer completed;
    private Double completion_rate;
    private Integer success;
    private Double success_rate;
    private Double avg_seconds;
    private Double avg_seconds_per_question;
}


