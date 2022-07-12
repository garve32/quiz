package com.ict.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Question extends Criteria{

    private Long id;
    private String text;
    private byte[] image;
    private int seq;
    private String use_yn;
    private Long category_id;

    /** 페이징 정보 */
    public Pagination pagination;

}
