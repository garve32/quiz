package com.ict.quiz.domain;

import lombok.Data;

@Data
public class QuestionPage extends Criteria{

    private Long id;
    private String text;
    private byte[] image;
    private int seq;
    private String use_yn;
    private String type;
    private Long category_id;
    private String category_nm;

    /** 페이징 정보 */
    public Pagination pagination;

}
