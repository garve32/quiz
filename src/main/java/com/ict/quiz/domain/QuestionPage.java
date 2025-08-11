package com.ict.quiz.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
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

    @Override
    public String makeQueryString(int pageNo) {
        String base = super.makeQueryString(pageNo);
        String categoryQuery = (category_id != null) ? (base.isEmpty() ? "?" : "&") + "category_id=" + category_id : "";
        String searchQuery = (getSearchKeyword() != null && !getSearchKeyword().isEmpty()) ? (categoryQuery.isEmpty() && base.isEmpty() ? "?" : "&") + "searchKeyword=" + getSearchKeyword() : "";
        return base + categoryQuery + searchQuery;
    }
}
